EXTRA_OEMESON:append = " -Dpycamera=enabled"

# Lots of runtime dependencies
DEPENDS += "python3 python3-pybind11 python3-numpy"
# TODO: These might all be dependencies of numpy
#RDEPENDS:${PN} += "python3-json python3-logging python3-pickle python3-multiprocessing python3-prctl"
RDEPENDS:${PN} += "python3-pillow python3-piexif python3-pidng"

# PKG_CONFIG_PATH:append = "${WORKDIR}/usr/lib/pkgconfig"

#TODO: Hacked up git/src/py/libcamera/meson.build to remove pybind11 dependency references
# Had to change pybind11 to the smart_holder branch because libcamera's src/py/libcamera/meson.build
# has -DPYBIND11_USE_SMART_HOLDER_AS_DEFAULT


# TODO: QA warning for tmpdir in the site-packages/_libcamera.so
FILES:${PN} += " ${PYTHON_SITEPACKAGES_DIR}/*"

#SRCREV = "6f9202b8eb1ea14c9db572377d778d40d422a846"