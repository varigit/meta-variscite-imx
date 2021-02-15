# Disable systemd-networkd and systemd-resolved services
do_configure_append() {
    sed -i -e "s/enable systemd-networkd.service/disable systemd-networkd.service/g" ${S}/presets/90-systemd.preset
    sed -i -e "s/enable systemd-resolved.service/disable systemd-resolved.service/g" ${S}/presets/90-systemd.preset
}
