#@DESCRIPTION: Linux for Variscite i.MX boards
#
# http://www.variscite.com

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "imx-rel_imx_4.1.15_2.0.0_ga-var02"

LOCALVERSION_var-som-mx6 = "-6qp"
LOCALVERSION_imx6ul-var-dart = "-6ul"
LOCALVERSION_imx7-var-som = "-7dual"

SRCREV = "76bd3bfa41dfae7674ece94e30993e06f6d620a6"
KERNEL_SRC ?= "git://github.com/varigit/linux-2.6-imx.git;protocol=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

DEFAULT_PREFERENCE = "1"

KERNEL_DEFCONFIG_var-som-mx6 = "imx_v7_var_defconfig"
KERNEL_DEFCONFIG_imx6ul-var-dart = "imx6ul-var-dart_defconfig"
KERNEL_DEFCONFIG_imx7-var-som = "imx7-var-som_defconfig"

do_preconfigure_prepend() {
   cp ${S}/arch/arm/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig
}

do_configure_prepend() {
   # delete old .config from source code
   rm ${S}/.config || true
}

# Copy the config file required by ti-compat-wirless-wl18xx
do_deploy_append () {
   cp ${S}/arch/arm/configs/${KERNEL_DEFCONFIG} ${S}/.config
}

COMPATIBLE_MACHINE = "(var-som-mx6|imx6ul-var-dart|imx7-var-som)"
