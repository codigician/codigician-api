package com.codigician.core.codexec.domain;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GenericContainer {
    private final DockerClient dockerClient;
    private final String id;
    private int activeTasks;

    GenericContainer(DockerClient dockerClient, String id) {
        this.dockerClient = dockerClient;
        this.id = id;
        this.activeTasks = 0;
    }

    public String execute(String... commands) throws InterruptedException {
        var executeCreateCommand = dockerClient.execCreateCmd(id)
                .withCmd(commands)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .withAttachStdin(true)
                .exec();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Data data = new Data();
        var executeStartCommand = dockerClient.execStartCmd(executeCreateCommand.getId())
                .exec(new SynchronizedStartCommand(countDownLatch, data));
        boolean awaitAlreadyDone = countDownLatch.await(1, TimeUnit.SECONDS);
        return executeStartCommand.data.getRawCommandOutput();
    }

    public boolean health() {
        try {
            dockerClient.pingCmd().exec();
            return true;
        } catch (Exception ignored) {
            System.out.printf("Container %s is not available, terminate the current container and create a new one.\n", id);
            return false;
        }
    }

    private static class Data {
        private String rawCommandOutput;

        public void setRawCommandOutput(String rawCommandOutput) {
            this.rawCommandOutput = rawCommandOutput;
        }

        public String getRawCommandOutput() {
            return rawCommandOutput;
        }
    }

    static class SynchronizedStartCommand implements ResultCallback<Frame> {
        private final Data data;
        private final CountDownLatch lock;

        SynchronizedStartCommand(CountDownLatch lock, Data data) {
            this.data = data;
            this.lock = lock;
        }

        @Override
        public void onStart(Closeable closeable) {
            System.out.println("Command started..");
        }

        @Override
        public void onNext(Frame o) {
            String rawCommandOutput = new String(o.getPayload(), StandardCharsets.UTF_8);
            data.setRawCommandOutput(rawCommandOutput);
            lock.countDown();
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("Command on error..");
        }

        @Override
        public void onComplete() {
            System.out.println("Command on complete..");
        }

        @Override
        public void close() throws IOException {
            System.out.println("Command closed..");
        }
    }
}
