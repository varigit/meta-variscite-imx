LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += "bison-native"

UBOOT_SRC ?= "git://github.com/varigit/uboot-imx;protocol=git"

SRCBRANCH = "lf-5.4.y_v2019.04_var01"
SRCREV = "4758f8090d68f284cd7a553f759e5eb7af90c5e3"

SRCBRANCH_imx6ul-var-dart = "imx_v2018.03_4.14.78_1.0.0_ga_var02"
SRCREV_imx6ul-var-dart = "f3764c66fe7d0bdaa3363f63c70239e0c30cb355"

SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"
