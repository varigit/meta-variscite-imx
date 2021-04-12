SRC_URI = "${FSL_MIRROR}/firmware-imx-${PV}.bin;fsl-eula=true \
           git://github.com/NXP/imx-firmware.git;branch=${SRCBRANCH};destsuffix=${S}/git "

do_install_append() {
	rm -rf ${D}${nonarch_base_libdir}/firmware/bcm
}

FILES_${PN}_remove += "${nonarch_base_libdir}/firmware/bcm/*"

