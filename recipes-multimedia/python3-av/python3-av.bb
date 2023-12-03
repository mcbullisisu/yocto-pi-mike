SUMMARY = "Pythonic binding for the FFmpeg libraries"
DESCRIPTION = "Pythonic binding for the FFmpeg libraries"

LICENSE = "BSD-3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a9c4cea4308c4521674ecd7c3255d8af"

SRC_URI = "git://github.com/PyAV-Org/PyAV.git;protocol=https;branch=main"
SRCREV = "cf8a5c39c0a03ef9c99ed3da1ab1121eb5633996"

S = "${WORKDIR}/git"
DEPENDS += "python3-cython-native ffmpeg"
RDEPENDS:${PV} = "ffmpeg libxcb v4l-utils libepoxy kmsxx"
inherit setuptools3 pkgconfig

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"