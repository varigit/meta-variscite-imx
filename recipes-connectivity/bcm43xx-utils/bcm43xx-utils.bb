DESCRIPTION = "Startup and config files for use with BCM43XX WIFI and Bluetooth"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES','systemd','','update-rc.d-native',d)}"

SRC_URI = " \
	file://variscite-wifi \
	file://variscite-wifi-common.sh \
	file://variscite-wifi.service \
	file://variscite-wifi.conf \
	file://99-iw61x-unmanaged-devices.conf \
	file://var_wifi_mod_para.conf \
"

PACKAGECONFIG ??= "networkmanager"
PACKAGECONFIG[networkmanager] = "--with-networkmanager,--without-networkmanager"

PACKAGES += "${PN}-iw61x"

FILES_${PN} = " \ 
	/etc/wifi/*  \
	/etc/init.d/* \
	/etc/rcS.d/* \
	/etc/modprobe.d \
	/lib/systemd/system/* \
	/etc/systemd/system/* \
"

FILES_${PN}-iw61x = " \
	/etc/NetworkManager/conf.d/* \
	/lib/firmware/nxp/var_wifi_mod_para.conf \
"

RDEPENDS_${PN}_imx8mq-var-dart = "i2c-tools"
RDEPENDS_${PN}_imx8mm-var-dart = "i2c-tools"
RDEPENDS_${PN}_imx8mn-var-som = "i2c-tools"
RDEPENDS_${PN}_append = " base-files"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}/wifi
	install -m 0644 ${WORKDIR}/variscite-wifi.conf ${D}${sysconfdir}/wifi
	install -m 0644 ${WORKDIR}/variscite-wifi-common.sh ${D}/${sysconfdir}/wifi
	install -m 0755 ${WORKDIR}/variscite-wifi ${D}/${sysconfdir}/wifi

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}${systemd_unitdir}/system
		install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/variscite-wifi.service ${D}/${systemd_unitdir}/system
 
		ln -sf ${systemd_unitdir}/system/variscite-wifi.service \
			${D}${sysconfdir}/systemd/system/multi-user.target.wants/variscite-wifi.service
	else
		install -d ${D}${sysconfdir}/init.d
		ln -s ${sysconfdir}/wifi/variscite-wifi ${D}${sysconfdir}/init.d/variscite-wifi
		update-rc.d -r ${D} variscite-wifi start 5 S .
	fi

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'networkmanager', 'yes', 'no', d)}" = "yes" ]; then
		install -d ${D}/${sysconfdir}/NetworkManager/conf.d
		install -m 0644 ${WORKDIR}/99-iw61x-unmanaged-devices.conf ${D}/${sysconfdir}/NetworkManager/conf.d
	fi

	install -d ${D}${nonarch_base_libdir}/firmware/nxp
	install -m 0755 ${WORKDIR}/var_wifi_mod_para.conf ${D}${nonarch_base_libdir}/firmware/nxp
}
