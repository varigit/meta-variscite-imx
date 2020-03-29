FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://imx8m-atf-fix-derate-enable.patch \
	file://imx8mm-atf-uart4.patch \
"
