require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "openssh"
#IMAGE_INSTALL += "picamera-libs"
IMAGE_INSTALL += "python3-picamera"
#IMAGE_INSTALL += "rpi-libcamera-apps"
#IMAGE_INSTALL += "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad"

PACKAGE_EXCLUDE:append = "bluez5 bluez5-tools"