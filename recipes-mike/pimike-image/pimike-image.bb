require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "openssh"
IAMGE_INSTALL += "picamera-libs"
IMAGE_INSTALL += "python3-picamera"
IMAGE_INSTALL += "python3-picamera2"
IMAGE_INSTALL += "rpi-libcamera-apps"
IMAGE_INSTALL += "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad"
