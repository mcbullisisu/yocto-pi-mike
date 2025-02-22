# yocto-pi-mike

Yocto core-image-minimal based distrubtion targetting the Raspberry Pi 3.

This distribution adds a custom utility, pimike-camera, that starts monitoring a PIR sensor on boot. If motion is detected via the PIR sensor then the PiCamera preview is shown on the display. I briefly tried this on a Pi 5, but I ran into issues with the legacy python3-picamera library. See <https://github.com/agherzan/meta-raspberrypi/pull/1237#issuecomment-1902725107>.

My public SSH key has been added to facilitate passwordless SSH login & Ethernet has been set to the static IP address of 192.168.168.2 for ease of development.

## Building without docker

The build has only been tested on Ubuntu 24.04 & 24.04.2. Containerized should be investigated with <https://github.com/mcbullisisu/yocto-pi-mike/issues/2>.

### Ubuntu 24.04 dependencies

```sh
sudo apt install chrpath gawk lz4
```

`./build.sh` should let you build the entire image. This will download the additional layer dependendencies of meta-openembedded, meta-raspberrypi, & poky. See setup-layers.json if you want to see the git URLs & commit hashes used. If you want to build individual packages with BitBake after the initial build, then you can run `source downloaded-layers/poky/oe-init-build-env` from the root of the repository.

## Building with docker

Using the crops/poky-container, we can minimize having to manually install any dependencies, like [the Ubuntu 24.04 ones mentioned above](#ubuntu-2404-dependencies).

:warning: You will have to do a clean build by deleting or renaming the build directory if you're switching between docker & non-docker builds. I have not yet created the scripting to have layer dependency paths for poky, meta-openembedded, & meta-raspberrypi be consistent inside & outside of docker.

## Prerequisites

1. Docker installed
    * Ubuntu 24.04 instructions borrowed from <https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository>

        ```sh
        # Add Docker's official GPG key:
        sudo apt-get update
        sudo apt-get install ca-certificates curl
        sudo install -m 0755 -d /etc/apt/keyrings
        sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
        sudo chmod a+r /etc/apt/keyrings/docker.asc

        # Add the repository to Apt sources:
        echo \
        "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
        $(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}") stable" | \
        sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
        sudo apt-get update

        # Install the latest version
        sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
        ```

2. Your normal, non-root user allowed to use docker
    * Ubuntu 24.04 instructions
        1. Add your user to the  docker group - `sudo usermod -a -G docker YourUserName`. This gives to files that can only be used with by the root user or docker group, without having to run sudo.
        2. Either log out & log back in, or run `newgrp docker` to update the list of groups in your current login session. Maybe `newgrp -` is more appropriate? Unsure & I haven't tried it yet

You should now be able to enter the crops/poky container with `docker run --rm -it -v ${PWD}:/workdir crops/poky --workdir=/workdir` & run the same `./build.sh` script.
