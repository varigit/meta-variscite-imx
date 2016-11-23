LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

S = "${WORKDIR}"

SRC_URI = "file://runonkeyrls.c \
           file://runonkeyrls.sh \
	   file://runonkeyrls.service \
"
inherit systemd

SYSTEMD_SERVICE_${PN} = "runonkeyrls.service"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -o runonkeyrls runonkeyrls.c
}

do_install() {
	install -d ${D}${bindir}/
	install -m 0755 ${S}/runonkeyrls ${D}${bindir}/
	install -m 0755 ${S}/runonkeyrls.sh ${D}${bindir}/
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${S}/runonkeyrls.service ${D}${systemd_unitdir}/system
        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
               -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
               -e 's,@SBINDIR@,${sbindir},g' \
               -e 's,@BINDIR@,${bindir},g' \
               -e 's,@SYS_CONFDIR@,${sysconfdir},g' \
               ${D}${systemd_unitdir}/system/runonkeyrls.service
}
