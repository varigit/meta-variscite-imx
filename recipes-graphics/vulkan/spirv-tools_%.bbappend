SRC_URI_remove = " \
    git://github.com/KhronosGroup/SPIRV-Tools;protocol=http;name=spirv-tools \
    git://github.com/KhronosGroup/SPIRV-Headers;name=spirv-headers;destsuffix=${SPIRV_HEADERS_LOCATION} \
"

SRC_URI_append = " \
    git://github.com/KhronosGroup/SPIRV-Tools;protocol=https;branch=main;name=spirv-tools \
    git://github.com/KhronosGroup/SPIRV-Headers;protocol=https;branch=main;name=spirv-headers;destsuffix=${SPIRV_HEADERS_LOCATION} \
"
