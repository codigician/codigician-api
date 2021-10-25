package com.codigician.core.codexec.domain;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.google.common.io.Resources;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GenericContainerPool {
    private final Map<String, GenericContainer> containers = new HashMap<>();
    private final DockerClient dockerClient;
    private final int minActiveContainerCount;
    private int activeContainerCount;

    public GenericContainerPool() {
        activeContainerCount = 0;
        minActiveContainerCount = 3;

        DockerClientConfig configuration = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        dockerClient = DockerClientBuilder.getInstance(configuration).build();

        reloadActiveCustomUbuntuContainers();
        generateNewContainers();
        runContainerManagementSystem();
    }

    public GenericContainer get() {
        GenericContainer firstGenericContainerInMap = null;
        for (Map.Entry<String, GenericContainer> containerEntry : containers.entrySet()) {
            firstGenericContainerInMap = containerEntry.getValue();
            break;
        }
        return firstGenericContainerInMap;
    }

    public void metrics() {
        System.out.printf("""
                        Minimum Active Container Count: %d
                        Active Container Count: %d
                        """,
                minActiveContainerCount, activeContainerCount);
    }

    public void reloadActiveCustomUbuntuContainers() {
        var listContainersCommand = dockerClient.listContainersCmd().exec();
        for (var dockerContainer : listContainersCommand) {
            String imageName = dockerContainer.getImage();
            boolean isActiveContainerImageCustomUbuntu = imageName.equals("custom-ubuntu");
            if (isActiveContainerImageCustomUbuntu) {
                GenericContainer genericContainer = new GenericContainer(dockerClient, dockerContainer.getId());
                containers.put(dockerContainer.getId(), genericContainer);
                activeContainerCount++;
            }
        }
    }

    private void generateNewContainers() {
        for (int i = 0; i < minActiveContainerCount - activeContainerCount; i++) {
            String containerId = createContainerWithDefaultImage();
            GenericContainer genericContainer = new GenericContainer(dockerClient, containerId);
            containers.put(containerId, genericContainer);
            activeContainerCount++;
        }
    }

    private String createContainerWithDefaultImage() {
        var createContainerCommand = dockerClient
                .createContainerCmd("custom-ubuntu")
                .withTty(true)
                .withAttachStdin(true)
                .withAttachStderr(true)
                .withAttachStdout(true)
                .exec();

        dockerClient.startContainerCmd(createContainerCommand.getId()).exec();

        return createContainerCommand.getId();
    }

    private void runContainerManagementSystem() {
        // Create N thread for N containers and ping continuously
        for (Map.Entry<String, GenericContainer> containerEntry : containers.entrySet()) {
            String containerId = containerEntry.getKey();
            GenericContainer currentContainer = containerEntry.getValue();
            boolean doesContainerHealthy = currentContainer.health();
            if (!doesContainerHealthy) {
                System.out.printf("Container %s is down...\n", containerId);
                activeContainerCount--;
            }
            System.out.printf("Container %s is active...\n", containerId);
        }
    }

}
