FILESEXTRAPATHS:append := "${THISDIR}/files:"

SRC_URI:append = "file://test_camera.py"

FILES:${PN} += "/usr/bin/test_camera.py"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: There is probably a better way to do my `#!/usr/bin/python3` shebang,
# but this is what I have now to satisfy do_package_qa
RDEPENDS:${PN} = "python3-core"

do_install:append () {
    install -d ${D}/${bindir}
    install ${WORKDIR}/test_camera.py ${D}/${bindir}
}