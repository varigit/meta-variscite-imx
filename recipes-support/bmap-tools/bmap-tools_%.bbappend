BRANCH_SUFFIX = ";branch=main"
SRC_URI := "${SRC_URI}${@ '${BRANCH_SUFFIX}' if ';branch=' not in d.getVar('SRC_URI') else '' }"
