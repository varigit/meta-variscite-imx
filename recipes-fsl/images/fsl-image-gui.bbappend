IMAGE_INSTALL += " \
	android-tools \
	android-tools-adbd \
	android-tools-fstools \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
	   bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d), d)} \
	libgpiod \
	libgpiod-tools \
"
