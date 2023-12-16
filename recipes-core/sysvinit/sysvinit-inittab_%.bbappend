RDEPENDS:${PN} += "e2fsprogs-resize2fs parted"

pkg_postinst_ontarget:${PN}:append () {
# Make the end of the root partition point to the end of the micro SD
parted /dev/mmcblk0 -s "resizepart 2 -1"
# Expand the filesystem on the partition to 5GB in size
# Expanding the filesystem online to the max size of the micro SD
# has show to take on the order of 10+ minutes for a 64GB Samsung
# Evo Plus Class 10 micro SD card, but making it 5GB only adds ~5 seconds
# of extra time to the first boot.
# TODO: A fancier solution would check the current size and add X GB extra
#       to ensure this solution works if the generated rootfs is bigger
#       than 5GB
resize2fs -p /dev/mmcblk0p2 5G
}