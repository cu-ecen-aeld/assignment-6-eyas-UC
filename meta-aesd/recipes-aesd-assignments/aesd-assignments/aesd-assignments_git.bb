# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-eyas-UC;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "c1557dda0baa13457f747d90e8315ac6d6193adb"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/server"

# installing init.d
# inherit update-rc.d
# INITSCRIPT_PACKAGES = "${PN}"
# INITSCRIPT_names:${PN} = "START_SCRIPT_NAME_HERE"
# this part is here and the above part is up in the receipe top

# TODO: Add the aesdsocket application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone
FILES:${PN} += "${bindir}/aesdsocket"
# TODO: customize these as necessary for any libraries you need for your application
# (and remove comment)
TARGET_LDFLAGS += "-pthread -lrt"
TARGET_CFLAGS += "-Wall -g -Werror"
TARGET ?= "aesdsocket"
# TARGET_CROSS_COMPILE ?= 

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
	# and
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
    install -d ${D}${bindir}
	# echo "S = ${S}"
	# echo "D = ${D}"
	# echo "D/bindir = ${D}${bindir}"
	# echo "ls -al S/aesdsocket"
	# echo "ls -al ${S}/aesdsocket"
	# ls -al ${S}/aesdsocket
	# echo "ls -al ${S}"
	# ls -al ${S}
	# echo "ls -al ${D}${bindir}"
	# ls -al ${D}${bindir}
	# echo "ls -al"
	# ls -al 
	
    install -m 0755 ${S}/aesdsocket ${D}${bindir}
    install -m 0755 ${S}/S99aesdsocket ${D}${bindir}
	echo "installed something !!!!!!!!!!!!!!!!!!!!!!!!!!!!!"

}
