# Copyright (C) 2021 Variscite
include freertos-variscite.inc

SRCREV = "a8b9a7ea089d791cd30c00cc691c2768f83cc307"
# See https://github.com/varigit/freertos-variscite/blob/mcuxpresso_sdk_2.9.x-var01/docs/MCUXpresso%20SDK%20Release%20Notes%20for%20EVK-MIMX8MN.pdf
# "Development Tools" section for supported GCC version
CM_GCC = "gcc-arm-none-eabi-9-2020-q2-update"

SRC_URI += " \
    git://github.com/varigit/freertos-variscite.git;protocol=git;branch=${MCUXPRESSO_BRANCH}; \
    https://developer.arm.com/-/media/Files/downloads/gnu-rm/9-2020q2/gcc-arm-none-eabi-9-2020-q2-update-x86_64-linux.tar.bz2;name=gcc-arm-none-eabi-9-2020-q2-update \
"

SRC_URI[gcc-arm-none-eabi-9-2020-q2-update.sha256sum] = "5adc2ee03904571c2de79d5cfc0f7fe2a5c5f54f44da5b645c17ee57b217f11f"

COMPATIBLE_MACHINE = "(imx8mn-var-som|imx8mm-var-dart|imx8mp-var-dart|imx8mq-var-dart|imx8qm-var-som|imx8qxp-var-som|imx8qxpb0-var-som)"
