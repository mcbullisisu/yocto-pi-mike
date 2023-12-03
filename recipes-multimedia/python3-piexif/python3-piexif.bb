SUMMARY = "Python bindings for exif"
DESCRIPTION = "To simplify exif manipulations with Python. Writing, reading, and more... Piexif is pure Python. To everywhere with Python."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d460f647631d11111d53da700a401cc9"

SRC_URI = "git://github.com/hMatoba/Piexif.git;protocol=https;branch=master"
SRCREV = "3422fbe7a12c3ebcc90532d8e1f4e3be32ece80c"

S = "${WORKDIR}/git"

inherit setuptools3

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"