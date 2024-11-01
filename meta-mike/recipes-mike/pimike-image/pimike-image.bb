require recipes-core/images/core-image-base.bb

# Disable the splash screen to save some boot time
IMAGE_FEATURES:remove = "splash"
# Get a full-featured dmesg instead of busybox dmesg
IMAGE_INSTALL += "util-linux"
IMAGE_INSTALL += "openssh"
IMAGE_INSTALL += "pimike-camera"
IMAGE_INSTALL += "htop"
IMAGE_INSTALL += "lmsensors"
# These are now runtime dependencies, and hence automatically installed by pimike-camera
# IMAGE_INSTALL += "python3-picamera"
# IMAGE_INSTALL += "rpi-gpio"
# IMAGE_INSTALL += "python3-logging"

# I was probably using these when I was unsuccessfully trying to build python3-picamera2 from source
#IMAGE_INSTALL += "picamera-libs"
#IMAGE_INSTALL += "rpi-libcamera-apps"
# I'm unsure how much gstreamer adds to the base image install size
#IMAGE_INSTALL += "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad"

PACKAGE_EXCLUDE:append = "bluez5 bluez5-tools"