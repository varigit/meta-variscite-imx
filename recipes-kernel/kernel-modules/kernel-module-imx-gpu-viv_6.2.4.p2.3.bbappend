FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
        file://0001-gpu-imx-Enable-GPU-workaround-for-i.MX8M-Mini-with-4.patch \
"


