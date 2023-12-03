SUMMARY = "libcamera-based replacement for Picamera"
DESCRIPTION = "libcamera-based replacement for Picamera"
HOMEPAGE = "https://datasheets.raspberrypi.com/camera/picamera2-manual.pdf" 

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6541a38108b5accb25bd55a14e76086d"

RDEPENDS:${PN} = "libcamera \
                  python3-numbers   \
                  python3-ctypes    \
                  python3-colorzero \
                  picamera-libs     \
"

SRC_URI = "git://git@github.com/raspberrypi/picamera2.git;protocol=ssh;branch=main"
SRCREV = "6f9202b8eb1ea14c9db572377d778d40d422a846"

S = "${WORKDIR}/git"

inherit setuptools3

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"