IMAGE_INSTALL += " \
	android-tools \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
	   bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d), d)} \
"

systemd_disable_vt () {
    rm ${IMAGE_ROOTFS}${root_prefix}${sysconfdir}/systemd/system/getty.target.wants/getty@tty*.service
}

IMAGE_PREPROCESS_COMMAND_append = " ${@ 'systemd_disable_vt;' if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d) and bb.utils.contains('USE_VT', '0', True, False, d) else ''} "
