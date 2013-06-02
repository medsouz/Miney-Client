import urllib, zipfile, os, sys, shutil
print"==================================="
print"|   __  ___   _                   |"
print"|  /  |/  /  (_)  ___  ___   __ __|"
print"| / /|_/ /  / /  / _ \/ -_) / // /|"
print"|/_/  /_/  /_/  /_//_/\__/  \_, / |"
print"|                          /___/  |"
print"==================================="
print "Cleaning the repository..."
if(os.path.exists("../forge/")):
	print "Removing Forge"
	if(os.path.exists("../forge/mcp/src/minecraft/net/medsouz/miney/client")):
		os.rmdir("../forge/mcp/src/minecraft/net/medsouz/miney/client")
	if(os.path.exists("../forge/mcp/src/minecraft/net/medsouz/miney/common")):
		os.rmdir("../forge/mcp/src/minecraft/net/medsouz/miney/common")
	if(os.path.exists("../forge/mcp/src/minecraft/net/medsouz/miney/updater")):
		os.rmdir("../forge/mcp/src/minecraft/net/medsouz/miney/updater")
	shutil.rmtree("../forge/")
else:
	print "Forge not found"
if(os.path.exists("../Miney-Updater-master/")):
	print "Removing Miney-Updater"
	shutil.rmtree("../Miney-Updater-master/")
else:
	print "Miney-Updater not found"
print"==================================="
print "Downloading recommended Forge build..."
urllib.urlretrieve ("http://files.minecraftforge.net/minecraftforge/minecraftforge-src-recommended.zip", "forge.zip")
print "Forge downloaded! Extracting..."
with zipfile.ZipFile("forge.zip", "r") as z:
	z.extractall("../");
print "Forge has been extracted, cleaning up zip file..."
os.remove("forge.zip")
print"==================================="
print "Downloading Miney-Updater..."
urllib.urlretrieve ("https://github.com/medsouz/Miney-Updater/archive/master.zip", "updater.zip")
print "Miney-Updater downloaded! Extracting..."
with zipfile.ZipFile("updater.zip", "r") as z:
	z.extractall("../");
print "Miney-Updater has been extracted, cleaning up zip file..."
os.remove("updater.zip")
print"==================================="
print "Changing directory to forge\'s directory..."
os.chdir("../forge/")
print "Running forge install script..."
if(sys.platform.startswith("linux") or sys.platform.startswith("darwin")):
	os.system("python install.py")
	os.system("mkdir ../forge/mcp/src/minecraft/net/medsouz/")
	os.system("mkdir ../../../../../../../forge/mcp/src/minecraft/net/medsouz/miney/")
	os.system("ln -s ../../../../../../../src/net/medsouz/miney/client ../forge/mcp/src/minecraft/net/medsouz/miney/client")
	os.system("ln -s ../../../../../../../src/net/medsouz/miney/common ../forge/mcp/src/minecraft/net/medsouz/miney/common")
	os.system("ln -s ../../../../../../../Miney-Updater-master/src/net/medsouz/miney/updater ../forge/mcp/src/minecraft/net/medsouz/miney/updater")
elif(sys.platform.startswith("win32")):
	os.system("install.cmd")
	os.system("mkdir ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\")
	os.system("mklink /j ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\client ..\\src\\net\\medsouz\\miney\\client")
	os.system("mklink /j ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\common ..\\src\\net\\medsouz\\miney\\common")
	os.system("mklink /j ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\updater ..\\Miney-Updater-master\\src\\net\\medsouz\\miney\\updater")
print"==================================="
print "If there is no errors above then Forge has been installed and Miney's source was linked"
print "You can now use the forge folder in Miney like any other Forge workspace."
print "The code is linked to the repository so that it is there whenever you need to commit."
print"==================================="