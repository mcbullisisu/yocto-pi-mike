# SUMMARY = "Fast JPEG encoding and decoding in Python3"
# DESCRIPTION = "simplejpeg is a simple package based on recent versions of libturbojpeg for fast JPEG encoding and decoding."

# LICENSE = "MIT"
# LIC_FILES_CHKSUM = "file://LICENSE;md5=1d325f91caffc43417b4c7b97d45f574"

# SRC_URI = "git://gitlab.com/jfolz/simplejpeg.git;protocol=https;branch=master"
# SRCREV = "99a0834d2015baf5821b7aba0120695f3f9ce131"

# DEPENDS += "python3-cmake-native"

# S = "${WORKDIR}/git"

# inherit setuptools3

# COMPATIBLE_HOST = "null"
# COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"

SUMMARY = "A simple package for fast JPEG encoding and decoding."
HOMEPAGE = ""
AUTHOR = "Joachim Folz <joachim.folz@dfki.de>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file:///${S}/simplejpeg-1.7.2.dist-info/LICENSE;md5=c9d1d569fff8e33fb858ca6c83f423d1"

SRC_URI = "https://files.pythonhosted.org/packages/16/c7/a46e4b76918cf2463a22160af76cb1e3d7225ea796c7867face439824680/simplejpeg-1.7.2-cp311-cp311-manylinux_2_17_aarch64.manylinux2014_aarch64.whl;downloadfilename=simplejpeg-1.7.2-cp311-cp311-manylinux_2_17_aarch64.manylinux2014_aarch64.zip;subdir=${BP}"

SRC_URI[md5sum] = "dd3f3225fded4b43ac627dfb860a3243"
SRC_URI[sha256sum] = "012e28ef2bce17cbdd73e4a49cc485e5940158a2bcb161bf06092db7ed90cf72"

inherit python3-dir

do_unpack[depends] += "unzip-native:do_populate_sysroot"

INSANE_SKIP:${PN} += "already-stripped"

DEPENDS += "python3"

FILES:${PN} += "\
    ${libdir}/${PYTHON_DIR}/site-packages/simplejpeg/* \
    ${libdir}/${PYTHON_DIR}/site-packages/simplejpeg-${PV}.dist-info/* \
"

do_install() {
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/simplejpeg
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/simplejpeg-${PV}.dist-info
    install -m 644 ${S}/simplejpeg/* ${D}${libdir}/${PYTHON_DIR}/site-packages/simplejpeg/
    install -m 644 ${S}/simplejpeg-${PV}.dist-info/* ${D}${libdir}/${PYTHON_DIR}/site-packages/simplejpeg-${PV}.dist-info/
    # Need to rename dist-info/simplejpeg/_jpeg.pyi to _jpeg.py
}