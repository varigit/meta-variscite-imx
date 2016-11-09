#@DESCRIPTION: Linux for Variscite i.MX6Q/D/DL/S VAR-SOM-MX6 IMX6UL-VAR-DART VAR-SOM-MX7 family
#
# http://www.variscite.com
# support@variscite.com

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH_var-som-mx6 = "imx-rel_imx_4.1.15_1.2.0_ga-var01"
SRCBRANCH_mx6ul = "imx-rel_imx_4.1.15_1.2.0_ga-var01"
SRCBRANCH_mx7 = "imx-rel_imx_4.1.15_1.2.0_ga-var01"

LOCALVERSION_var-som-mx6 = "-6QP"
LOCALVERSION_mx6ul = "-6UL"
LOCALVERSION_mx7 = "-7Dual"

SRCREV = "${AUTOREV}"
KERNEL_SRC ?= "git://github.com/varigit/linux-2.6-imx.git;protocol=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

FSL_KERNEL_DEFCONFIG_var-som-mx6 = "imx_v7_var_defconfig"
FSL_KERNEL_DEFCONFIG_mx6ul = "imx6ul-var-dart_defconfig"
FSL_KERNEL_DEFCONFIG_mx7 = "imx7-var-som_defconfig"

KERNEL_IMAGETYPE = "zImage"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

do_preconfigure_prepend() {
   # copy latest defconfig for imx_v7_var_defoonfig to use
   cp ${S}/arch/arm/configs/${FSL_KERNEL_DEFCONFIG} ${B}/.config
   cp ${S}/arch/arm/configs/${FSL_KERNEL_DEFCONFIG} ${B}/../defconfig
}

do_configure_prepend() {
   # delete old .config from source code
   rm ${S}/.config || true
}

# Copy the config file required by ti-compat-wirless-wl18xx
do_deploy_append () {
   cp ${S}/arch/arm/configs/${FSL_KERNEL_DEFCONFIG} ${S}/.config
}


COMPATIBLE_MACHINE = "(var-som-mx6|mx6ul|mx7)"

DEFAULT_PREFERENCE = "1"

