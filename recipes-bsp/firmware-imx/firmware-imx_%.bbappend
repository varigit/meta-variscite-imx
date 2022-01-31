# NXP migrated from git.freescale.com to https://github.com/NXP/imx-firmware/
# During the migration, master branch was rebased, and 951c1363abe95dd75ab3e9447f640d7807240236
# is no longer valid. The dropped patch (commit id 033f0a20d2d5bbeb60c61bfa01ddf6119f7d8732)
# is not relevant.
SRCREV = "6beb28fb947f9c6fcf7fa46c708a3e1d05370955"
SRC_URI = "${FSL_MIRROR}/firmware-imx-${PV}.bin;fsl-eula=true \
           git://github.com/NXP/imx-firmware.git;protocol=https;branch=master;destsuffix=${S}/git;name=imx-firmware"

