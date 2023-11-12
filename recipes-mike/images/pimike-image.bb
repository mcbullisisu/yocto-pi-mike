require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "openssh"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI = "file://id_rsa.pub"

#S = "${WORKDIR}"

add_ssh_key () {
    echo "Mike was here"
    install -d ${IMAGE_ROOTFS}/home/root/.ssh
    #cat ${WORKDIR}/id_rsa.pub >> ${IMAGE_ROOTFS}/home/root/.ssh/authorized_keys
}

ROOTFS_POSTPROCESS_COMMAND += "add_ssh_key;"