LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/varigit/uboot-imx;protocol=https"
SRCBRANCH = "imx_v2018.03_4.14.98_2.0.0_ga_var01"
SRCBRANCH_imx8mn-var-som = "imx_v2018.03_4.14.98_2.3.0_var01"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
SRCREV = "8b7d9181bc039cec4b12c3e8b7d9906e65c0aa95"
SRCREV_imx8mn-var-som = "08808c6df97ee2f14f411551fdf2872929638798"

S = "${WORKDIR}/git"

