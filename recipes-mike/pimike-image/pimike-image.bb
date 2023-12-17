require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "openssh"
IMAGE_INSTALL += "python3-picamera"
IMAGE_INSTALL += "pimike-camera"
# I was probably using these when I was unsuccessfully trying to build python3-picamera2 from source
#IMAGE_INSTALL += "picamera-libs"
#IMAGE_INSTALL += "rpi-libcamera-apps"
# I'm unsure how much gstreamer adds to the base image install size
#IMAGE_INSTALL += "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad"

PACKAGE_EXCLUDE:append = "bluez5 bluez5-tools"