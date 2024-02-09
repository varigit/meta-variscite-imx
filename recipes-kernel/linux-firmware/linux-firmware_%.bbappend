# Support additional firmware for bc43xx WIFI+BT modules

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRCREV_FORMAT = "linux-firmware"
IMX_FIRMWARE_SRC = "git://github.com/varigit/imx-firmware.git;protocol=https"
SRCREV_imx-firmware = "f899d18fe944fb15ce07ba466cf60c11d05ec1cb"
SRCBRANCH = "lf-6.1.22_2.0.0-var01"

SRCREV_brcm = "7080491e10b82661ca4a67237fdb361190775d2f"
BRANCH_brcm = "7.0.0.142"
SRC_URI_append = " \
           git://github.com/varigit/bcm_4343w_fw.git;protocol=git;branch=${BRANCH_brcm};destsuffix=brcm;name=brcm \
"
do_install_append() {
        install -d ${D}${nonarch_base_libdir}/firmware/bcm
        install -m 0755 ${WORKDIR}/brcm/brcm/* ${D}${nonarch_base_libdir}/firmware/brcm/
        install -m 0755 ${WORKDIR}/brcm/*.hcd ${D}${nonarch_base_libdir}/firmware/bcm

        # Install NXP Connectivity IW612 firmware
        install -m 0644 ${WORKDIR}/imx-firmware/nxp/FwImage_IW612_SD/sduart_nw61x_v1.bin.se ${D}${nonarch_base_libdir}/firmware/nxp
        install -m 0644 ${WORKDIR}/imx-firmware/nxp/FwImage_IW612_SD/sd_w61x_v1.bin.se      ${D}${nonarch_base_libdir}/firmware/nxp
        install -m 0644 ${WORKDIR}/imx-firmware/nxp/FwImage_IW612_SD/uartspi_n61x_v1.bin.se ${D}${nonarch_base_libdir}/firmware/nxp
        for f in ${WORKDIR}/imx-firmware/nxp/FwImage_IW612_SD/IW612_SD_RFTest/*; do
            install -D -m 0644 $f ${D}${nonarch_base_libdir}/firmware/nxp/IW612_SD_RFTest/$(basename $f)
        done
}

PACKAGES =+ " \
  ${PN}-nxp-common \
  ${PN}-nxpiw612-sdio \
"

FILES_${PN}-bcm4339 += " \
  ${nonarch_base_libdir}/firmware/bcm/bcm4339.hcd \
  ${nonarch_base_libdir}/firmware/brcm/brcmfmac4339-sdio.txt \
"

FILES_${PN}-bcm43430 += " \
  ${nonarch_base_libdir}/firmware/bcm/bcm43430a1.hcd \
  ${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.txt \
"

FILES_${PN}-nxpiw612-sdio = " \
  ${nonarch_base_libdir}/firmware/nxp/sduart_nw61x_v1.bin.se \
  ${nonarch_base_libdir}/firmware/nxp/sd_w61x_v1.bin.se \
  ${nonarch_base_libdir}/firmware/nxp/uartspi_n61x_v1.bin.se \
  ${nonarch_base_libdir}/firmware/nxp/IW612_SD_RFTest/ \
"
RDEPENDS_${PN}-nxpiw612-sdio += "${PN}-nxp-common"

FILES_${PN}-nxp-common = " \
  ${nonarch_base_libdir}/firmware/nxp/wifi_mod_para.conf \
"
