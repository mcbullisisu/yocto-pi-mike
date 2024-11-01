#!/usr/bin/env bash

set -eu -o pipefail

# Ubuntu 24.04 workaround
# TODO: Mike didn't investigate this much
echo "Asking for temporary sudo permissions for Ubuntu 24.04 workaround"
echo "See https://lists.yoctoproject.org/g/yocto/topic/106192359#msg63138"
echo 0 | sudo tee /proc/sys/kernel/apparmor_restrict_unprivileged_userns

# Sourcing oe-init-build-env sets $THIS_SCRIPT
# and changes the current working directory to the build directory.
# Make an array of layer paths before sourcing oe-init-build-env
# using their download locations which are relative to the directory of this script.
THIS_SCRIPT="$(readlink -f $0)"
THIS_DIRECTORY="$(dirname ${THIS_SCRIPT})"
declare -ar LAYERS_TO_ADD=(
    "${THIS_DIRECTORY}/layers/meta-openembedded/meta-oe/"
    "${THIS_DIRECTORY}/layers/meta-openembedded/meta-python/"
    "${THIS_DIRECTORY}/layers/meta-openembedded/meta-multimedia/"
    "${THIS_DIRECTORY}/layers/meta-openembedded/meta-networking/"
    "${THIS_DIRECTORY}/layers/meta-raspberrypi/"
    "${THIS_DIRECTORY}/meta-mike/"
)

mkdir -p layers
(
    cd layers
    if ! [[ -d "poky" ]]; then
        git clone -v https://github.com/yoctoproject/poky.git
    fi
    pushd poky
    git checkout nanbield # 046b70083f3bc9e25f547e8026400032f5c563d9 was last confirmed working

    popd
    if ! [[ -d "meta-openembedded" ]]; then
        git clone -v https://github.com/openembedded/meta-openembedded.git
    fi
    pushd meta-openembedded
    git checkout nanbield # 1750c66ae8e4268c472c0b2b94748a59d6ef866d was last confirmed working
    popd

    if ! [[ -d "meta-raspberrypi" ]]; then
        git clone -v https://github.com/agherzan/meta-raspberrypi.git # fde68b24f08b0eac4ad4bfd3c461dc17fe3a263c was last confirmed work
    fi

    pushd meta-raspberrypi
    git checkout fde68b24f08b0eac4ad4bfd3c461dc17fe3a263c # nanbield
    popd
)

# TODO: oe-init-build-env: line 29: BBSERVER: unbound variable
set +eu
source layers/poky/oe-init-build-env
set -eu
echo "mike was here ${PWD}"


# (
#     cat >> conf/bblayers.conf << EOF
# BBLAYERS_append = "
#   \${TOP_DIR}/../ \\
#   \${TOP_DIR}/../layers/meta-raspberrypi \\
#   \${TOP_DIR}/../layers/meta-openembedded/meta-oe \\
#   \${TOP_DIR}/../layers/meta-openembedded/meta-multimedia \\
#   \${TOP_DIR}/../layers/meta-openembedded/meta-networking \\
#   \${TOP_DIR}/../layers/meta-openembedded/meta-python \\
#   "
# EOF
# )

for layer in ${LAYERS_TO_ADD[@]}; do
    bitbake-layers add-layer "${layer}"
done
# bitbake-layers add-layer "${THIS_DIRECTORY}/layers/meta-openembedded/meta-oe/"
# bitbake-layers add-layer "${THIS_DIRECTORY}layers/meta-openembedded/meta-python/"
# bitbake-layers add-layer "${THIS_DIRECTORY}layers/meta-openembedded/meta-multimedia/"
# bitbake-layers add-layer "${THIS_DIRECTORY}layers/meta-openembedded/meta-networking/"
# bitbake-layers add-layer "${THIS_DIRECTORY}layers/meta-raspberrypi/"
# bitbake-layers add-layer "${THIS_DIRECTORY}"

# Ubuntu 24.04 dependencies
# sudo apt install chrpath gawk lz4

bitbake core-image-minimal
