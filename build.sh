#!/usr/bin/env bash

set -eu -o pipefail

# Ubuntu 24.04 workaround
# TODO: Mike didn't investigate this much
# echo "Asking for temporary sudo permissions for Ubuntu 24.04 workaround"
# echo "See https://lists.yoctoproject.org/g/yocto/topic/106192359#msg63138"
# echo 0 | sudo tee /proc/sys/kernel/apparmor_restrict_unprivileged_userns

# Sourcing oe-init-build-env sets $THIS_SCRIPT
# and changes the current working directory to the build directory.
# Make an array of layer paths before sourcing oe-init-build-env
# using their download locations which are relative to the directory of this script.
THIS_SCRIPT="$(readlink -f $0)"
THIS_DIRECTORY="$(dirname ${THIS_SCRIPT})"
EXTERNAL_LAYERS_DIR="${THIS_DIRECTORY}/downloaded-layers"
declare -ar LAYERS_TO_ADD=(
    "${EXTERNAL_LAYERS_DIR}/meta-openembedded/meta-oe/"
    "${EXTERNAL_LAYERS_DIR}/meta-openembedded/meta-python/"
    "${EXTERNAL_LAYERS_DIR}/meta-openembedded/meta-multimedia/"
    "${EXTERNAL_LAYERS_DIR}/meta-openembedded/meta-networking/"
    "${EXTERNAL_LAYERS_DIR}/meta-raspberrypi/"
    "${THIS_DIRECTORY}/meta-mike/"
)

# Download dependant layers.
# Note this setup-layers script was created via
# `bitbake-layers create-layers-setup /path/to/some/folder`.
# I modified the generated setup-layers.json to remove references
# to this repository, yocto-pi-mike, because we've already cloned this repository.
# I also removed any leading directories from each layer's "path" entry in
# setup-layers.json so the setup-layers scripts clones them in a flat structure in
# ${EXTERNAL_LAYERS_DIR}.
./setup-layers --destdir "${EXTERNAL_LAYERS_DIR}"

# Enter the build environment
# TODO: oe-init-build-env: line 29: BBSERVER: unbound variable
set +u
source "${EXTERNAL_LAYERS_DIR}/poky/oe-init-build-env"
set -u

# Configure the layers used to build the project
for layer in ${LAYERS_TO_ADD[@]}; do
    bitbake-layers add-layer "${layer}"
done

bitbake pimike-image


