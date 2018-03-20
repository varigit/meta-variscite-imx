SUMMARY = "U-Boot for Variscite's i.MX boards"
require recipes-bsp/u-boot/u-boot.inc

inherit fsl-u-boot-localversion

LOCALVERSION_var-som-mx6 = "-mx6"
LOCALVERSION_imx6ul-var-dart = "-mx6ul"
LOCALVERSION_imx7-var-som = "-mx7"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "u-boot"

SRCBRANCH = "imx_v2015.04_4.1.15_1.1.0_ga_var03"
SRCBRANCH_imx7-var-som = "imx_v2017.03_4.9.11_1.0.0_ga_var01"
UBOOT_SRC = "git://github.com/varigit/uboot-imx.git;protocol=git"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
SRCREV_var-som-mx6 = "1dad2408add54118dc25f944f33ddbaf0944b993"
SRCREV_imx6ul-var-dart = "3dcc0ab7cb6f8921442b04a8c2eb9842030c25a7"
SRCREV_imx7-var-som = "3b5f889cb501c9fbe44c750c657dcd9ea4875176"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(var-som-mx6|imx6ul-var-dart|imx7-var-som)"
