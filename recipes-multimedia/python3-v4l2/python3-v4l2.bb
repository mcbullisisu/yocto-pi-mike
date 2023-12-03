SUMMARY = "Video for Linux 2 (V4L2) python library"
DESCRIPTION = "Video for Linux 2 (V4L2) python library"
HOMEPAGE = "https://github.com/tiagocoutinho/v4l2py" 

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

RDEPENDS:${PN} = "libcamera \
                  python3-numbers   \
                  python3-ctypes    \
                  python3-colorzero \
                  picamera-libs     \
"

#also need asyncio

SRC_URI = "git://git@github.com/antmicro/python3-v4l2.git;protocol=ssh;branch=master"
SRCREV = "e56ee8574b6dc15f305f23fb872f2038eccf715a"

S = "${WORKDIR}/git"

inherit setuptools3

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"