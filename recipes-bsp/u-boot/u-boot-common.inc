LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/varigit/uboot-imx;protocol=git"
SRCBRANCH = "imx_v2018.03_4.14.78_1.0.0_ga_var01"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
SRCREV = "fa79b1a7cdfcfcf981247f0331a3ca072f7075b0"

S = "${WORKDIR}/git"

