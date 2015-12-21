# This is a TI specific version of the compat-wireless recipe using a
# compat-wireless package created from the TI Systems Tested mac80211 releases.

DESCRIPTION = "ti compat-wireless drivers for wl18xx"
HOMEPAGE = "https://git.ti.com/wilink8-wlan/wl18xx"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../backports/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

RDEPENDS_${PN} = "wireless-tools"

PV = "R8.6"
inherit module

PR = "R1"

# Tags: R8.6
SRCREV_wl18xx = "3f5b34f607645d94c7ff5c5a01f93b8ba59e5574"
BRANCH_wl18xx = "upstream_41"
SRCREV_backports = "4677dc3f5d23242060a8ddc33e38838b2430ff61"
BRANCH_backports = "upstream_41"

SRCREV_FORMAT = "wl18xx"

S = "${WORKDIR}/compat-wireless"

SRC_URI = "git://git.ti.com/wilink8-wlan/wl18xx.git;branch=${BRANCH_wl18xx};destsuffix=wl18xx;name=wl18xx \
           git://git.ti.com/wilink8-wlan/backports.git;branch=${BRANCH_backports};destsuffix=backports;name=backports \
"

export KLIB_BUILD="${STAGING_KERNEL_DIR}"
export KLIB="${D}"

do_configure() {
    cd "${WORKDIR}/backports"
    unset CC
    #Generate compat-wireless
    python ./gentree.py --clean  "${WORKDIR}/wl18xx" "${WORKDIR}/compat-wireless"

    cd ${S}

    # Now generate the sourceipk with the properly configured sources
#    sourceipk_do_create_srcipk

    make defconfig-wl18xx
    sed -i 's/__TIMESTAMP__/"June_22_2015"/g' ${S}/drivers/net/wireless/ti/wlcore/Makefile
    sed -i 's/__TIMESTAMP__/"June_22_2015"/g' ${S}/drivers/net/wireless/ti/wlcore/release_version.h
    sed -i 's/\/\* time sync \*\//return hw; \/\* time sync \*\//g' ${S}/drivers/net/wireless/ti/wlcore/main.c

}

do_install() {
    # Install modules
    oe_runmake modules_install
}
