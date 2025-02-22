# yocto-pi-mike

Yocto core-image-minimal based distrubtion targetting the Raspberry Pi 3.

This distribution adds a custom utility, pimike-camera, that starts monitoring a PIR sensor on boot. If motion is detected via the PIR sensor then the PiCamera preview is shown on the display. I briefly tried this on a Pi 5, but I ran into issues with the legacy python3-picamera library. See <https://github.com/agherzan/meta-raspberrypi/pull/1237#issuecomment-1902725107>.

My public SSH key has been added to facilitate passwordless SSH login & Ethernet has been set to the static IP address of 192.168.168.2 for ease of development.

## Building

`./build.sh` should let you build the entire image without additional setup. This will download the additional layer dependendencies of meta-openembedded, meta-raspberrypi, & poky. See setup-layers.json if you want to see the git URLs & commit hashes used. If you want to build individual packages with BitBake after the initial build, then you can run `source downloaded-layers/poky/oe-init-build-env` from the root of the repository.

The build has only been tested on Ubuntu 24.04 & 24.04.2. Containerized should be investigated with <https://github.com/mcbullisisu/yocto-pi-mike/issues/2>.
