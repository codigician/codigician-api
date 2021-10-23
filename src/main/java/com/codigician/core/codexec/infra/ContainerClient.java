package com.codigician.core.codexec.infra;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ExecCreateCmd;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ContainerClient {
    private final DockerClient dockerClient;

    public ContainerClient() {
        DockerClientConfig config = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .build();

        dockerClient = DockerClientBuilder.getInstance(config).build();
    }

    public void run() {
        ListImagesCmd listImagesCmd = dockerClient.listImagesCmd();
        Info info = dockerClient.infoCmd().exec();
        System.out.println(info);
        List<Image> images = listImagesCmd.exec();
        images.forEach(System.out::println);


        String containerId = "89d39fa1ccb2";
        ExecCreateCmd execCreateCmd = dockerClient.execCreateCmd(containerId);
        ExecCreateCmdResponse response = execCreateCmd
                .withCmd("python", "--version")
                .withAttachStdout(true)
                .withAttachStderr(true)
                .withAttachStdin(true)
                .exec();
        System.out.println(response);
        System.out.println(response.getRawValues());

        dockerClient.execStartCmd(response.getId())
                .exec(new ResultCallback<Frame>() {
                    @Override
                    public void onStart(Closeable closeable) {
                        System.out.println("Result callback started..");
                    }

                    @Override
                    public void onNext(Frame frame) {
                        System.out.println(frame.getRawValues());
                        System.out.println(new String(frame.getPayload(), StandardCharsets.UTF_8));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Result callback on error");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Result callback completed");
                    }

                    @Override
                    public void close() throws IOException {
                        System.out.println("Result callback close");
                    }
                });
    }
}
