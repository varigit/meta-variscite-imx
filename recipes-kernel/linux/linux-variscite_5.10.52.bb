# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017 NXP
# Copyright 2018-2020 Variscite Ltd.
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux kernel provided and supported by Variscite"
DESCRIPTION = "Linux kernel provided and supported by Variscite (based on the kernel provided by NXP) \
with focus on i.MX Family SOMs. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

FILES_${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

DEPENDS += "lzop-native bc-native"

DEFAULT_PREFERENCE = "1"

SRCBRANCH = "lf-5.10.y_var03"
KERNEL_SRC ?= "git://github.com/varigit/linux-imx;protocol=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"
SRCREV = "df035f1e09c72552b4a1d5a19f5e0ae96f982e96"
LINUX_VERSION = "5.10.52"

KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LOCALVERSION_imx6ul-var-dart = "-imx6ul"
LOCALVERSION_imx8mp-var-dart = "-imx8mp"
LOCALVERSION_imx8mq-var-dart = "-imx8mq"
LOCALVERSION_imx8mm-var-dart = "-imx8mm"
LOCALVERSION_imx8mn-var-som = "-imx8mn"
LOCALVERSION_imx8qxp-var-som = "-imx8x"
LOCALVERSION_imx8qm-var-som = "-imx8qm"

KBUILD_DEFCONFIG_mx6 = "imx_v7_var_defconfig"
KBUILD_DEFCONFIG_mx8 = "imx8_var_defconfig"
KBUILD_DEFCONFIG_imx8mq-var-dart = "imx8mq_var_dart_defconfig"
DEFAULT_DTB_imx8mq-var-dart = "sd-lvds"
DEFAULT_DTB_imx8qxp-var-som = "sd"
DEFAULT_DTB_imx8qm-var-som = "lvds"
DEFAULT_DTB_PREFIX_imx8mq-var-dart = "imx8mq-var-dart"
DEFAULT_DTB_PREFIX_imx8qxp-var-som = "imx8qxp-var-som"
DEFAULT_DTB_PREFIX_imx8qm-var-som = "imx8qm-var-som"

#S = "${WORKDIR}/git"

#addtask copy_defconfig after do_patch before do_preconfigure
#addtask copy_defconfig after do_kernel_configme before do_preconfigure
#do_copy_defconfig () {
#    cp ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG} ${WORKDIR}/defconfig
#    cp ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG} ${B}/.config
#}

pkg_postinst_kernel-devicetree_append () {
   rm -f $D/boot/devicetree-*
}

pkg_postinst_kernel-devicetree_append_imx8mq-var-dart () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}-cb12.dtb ${DEFAULT_DTB_PREFIX}-cb12.dtb
}

pkg_postinst_kernel-devicetree_append_imx8qxp-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
}

pkg_postinst_kernel-devicetree_append_imx8qm-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
    ln -s imx8qm-var-spear-${DEFAULT_DTB}.dtb imx8qm-var-spear.dtb
}

KERNEL_VERSION_SANITY_SKIP="1"
COMPATIBLE_MACHINE = "(mx6|mx8)"
