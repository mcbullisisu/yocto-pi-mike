FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "file://id_rsa.pub"

FILES:${PN}-sshd += "/home/root/.ssh/authorized_keys"
CONFFILES:${PN}-sshd += "/home/root/.ssh/authorized_keys"

do_install:append () {
    echo "Mike was here"
    install -d ${D}/home/root/.ssh
    install ${WORKDIR}/id_rsa.pub ${D}/home/root/.ssh/authorized_keys
}