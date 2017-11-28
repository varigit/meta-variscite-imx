# This is a TI specific version of the hostap-daemon recipe for use with the
# wl18xx wlan and bluetooth module.

require hostap.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=292eece3f2ebbaa25608eed8464018a3"

FILESEXTRAPATHS_prepend := "${THISDIR}/hostap-daemon:"

# Add TI to the end to make it clear that this is a TI customized version
# of hostap
PV = "R8.7_SP3-ti"

# Tag: R8.7_SP3
SRCREV = "ee8fbdb840d95e048f58fb62bf3b5472041b5417"
BRANCH = "upstream_25_rebase"

PROVIDES += "hostap-daemon"
RPROVIDES_${PN} += "hostap-daemon"
RREPLACES_${PN} += "hostap-daemon"
RCONFLICTS_${PN} += "hostap-daemon"
