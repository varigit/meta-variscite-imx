LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/varigit/uboot-imx;protocol=git"

SRCBRANCH = "imx_v2018.03_4.14.98_2.0.0_ga_var01"
SRCBRANCH_imx8mn-var-som = "imx_v2018.03_4.14.98_2.3.0_var01"
SRCBRANCH_imx8qxp-var-som = "imx_v2018.03_4.14.98_2.3.0_var01"
SRCBRANCH_imx8qxpb0-var-som = "imx_v2018.03_4.14.98_2.3.0_var01"

SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"

SRCREV = "425836b51812390b258b1808da7e2d20c79371da"
SRCREV_imx8mn-var-som = "08808c6df97ee2f14f411551fdf2872929638798"
SRCREV_imx8qxp-var-som = "f27b3a701d206dcbb2a444fc2f6fbd5255eba38c"
SRCREV_imx8qxpb0-var-som = "f27b3a701d206dcbb2a444fc2f6fbd5255eba38c"

S = "${WORKDIR}/git"

