FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_imx8mq-var-dart = " \
	file://imx8m-atf-ddr-timing.patch \
	file://imx8m-atf-fix-derate-enable.patch \
"
SRC_URI_append_imx8mm-var-dart = " \
	file://imx8m-atf-ddr-timing.patch \
	file://imx8m-atf-fix-derate-enable.patch \
	file://imx8mm-atf-uart4.patch \
"
SRC_URI_append_imx8mn-var-som  = " \
	file://imx8m-atf-ddr-timing.patch \
	file://imx8mn-atf-uart4.patch \
"

