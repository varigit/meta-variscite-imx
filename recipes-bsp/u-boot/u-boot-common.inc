LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/varigit/uboot-imx;protocol=git"
SRCBRANCH = "imx_v2018.03_4.14.98_2.0.0_ga_var01"
SRCBRANCH_imx8mn-var-som = "imx_v2018.03_4.14.98_2.3.0_var01"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
SRCREV = "433901fa5a049131519cc124954e1e49d3a5f2d6"
SRCREV_imx8mn-var-som = "2e644ef790da94126909ac22f7273f75e5c30d97"

S = "${WORKDIR}/git"

