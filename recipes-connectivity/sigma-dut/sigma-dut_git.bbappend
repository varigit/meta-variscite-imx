# Fix source package fetching due to CAF been shut down
SRC_URI_remove = "git://source.codeaurora.org/quic/la/platform/vendor/qcom-opensource/wlan/utils/sigma-dut;protocol=https;branch=github-qca/master;"
SRC_URI_prepend = "git://git.codelinaro.org/clo/la/platform/vendor/qcom-opensource/wlan/utils/sigma-dut;protocol=https;branch=github-qca/master; "
