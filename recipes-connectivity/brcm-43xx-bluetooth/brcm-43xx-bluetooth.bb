DESCRIPTION = "Broadcom Bluetooth firmware download service"
SECTION = "connectivity"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
    file://10-local.rules \
    file://brcm-bt-firmware.service \
    file://brcm-bt-fw-download.sh \
    file://hciconf.sh \
"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE_${PN} = "brcm-bt-firmware.service"

RDEPENDS_${PN} = " \
    bluez5 \
"

do_install() {

        install -d  ${D}/etc/bluetooth/
        install -m 0755 ${WORKDIR}/brcm-bt-fw-download.sh ${WORKDIR}/hciconf.sh ${D}/etc/bluetooth/

        install -d ${D}/etc/udev/rules.d/
        install -m 0755 ${WORKDIR}/10-local.rules ${D}/etc/udev/rules.d/

        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/brcm-bt-firmware.service ${D}${systemd_unitdir}/system
        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
               -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
               -e 's,@SBINDIR@,${sbindir},g' \
               -e 's,@BINDIR@,${bindir},g' \
               -e 's,@SYS_CONFDIR@,${sysconfdir},g' \
               ${D}${systemd_unitdir}/system/brcm-bt-firmware.service
}

#PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(var-som-mx6|mx6ul|mx7)"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
