SUMMARY = "Python bindings for exif"
DESCRIPTION = "To simplify exif manipulations with Python. Writing, reading, and more... Piexif is pure Python. To everywhere with Python."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=092f551bd6b87930ba9ba190f3633972"

SRC_URI = "git://github.com/schoolpost/PiDNG.git;protocol=https;branch=master"
SRCREV = "66f3e16cf235443e4255a0ad382aa9d27bd1c095"

S = "${WORKDIR}/git"

inherit setuptools3

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"