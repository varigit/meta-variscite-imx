# Add --post-data option to avoid HTTP 304 error when downloading sof-imx from nxp.com,
# which occurs in some network environments.
FETCHCMD_wget = "/usr/bin/env wget -t 2 -T 30 --passive-ftp --no-check-certificate --post-data=0"

