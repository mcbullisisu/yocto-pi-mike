SRC_URI:append = "file://test_camera.py \
                  file://preview_camera_on_pir_motion.py \
                  file://pir-camera.sh"

FILES:${PN} += "${bindir}/test_camera.py"
FILES:${PN} += "${bindir}/preview_camera_on_pir_motion.py"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: There is probably a better way to do my `#!/usr/bin/python3` shebang,
# but this is what I have now to satisfy do_package_qa
RDEPENDS:${PN} = "bash python3-core rpi-gpio python3-logging python3-picamera"

inherit update-rc.d
INITSCRIPT_NAME = "pir-camera.sh"
INITSCRIPT_PARAMS = "start 70 5 ."

do_install:append () {
    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/test_camera.py ${D}/${bindir}
    install -m 0755 ${WORKDIR}/preview_camera_on_pir_motion.py ${D}/${bindir}

    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/pir-camera.sh ${D}${sysconfdir}/init.d/

}