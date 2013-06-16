import urllib, zipfile, os, sys, shutil

def runNative(cmd):
	out = os.system(cmd)
	if(out != 0):
		print("===================================")
		print("Miney setup failed with error code "+str(out))
		print("===================================")
		sys.exit(1)

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
                if(os.path.islink("../forge/mcp/src/minecraft/net/medsouz/miney/client")):
                        os.unlink("../forge/mcp/src/minecraft/net/medsouz/miney/client")
                else:
                        os.rmdir("../forge/mcp/src/minecraft/net/medsouz/miney/client")
        if(os.path.exists("../forge/mcp/src/minecraft/net/medsouz/miney/common")):
                if(os.path.islink("../forge/mcp/src/minecraft/net/medsouz/miney/common")):
                        os.unlink("../forge/mcp/src/minecraft/net/medsouz/miney/common")
                else:
                        os.rmdir("../forge/mcp/src/minecraft/net/medsouz/miney/common")
        if(os.path.exists("../forge/mcp/src/minecraft/net/medsouz/miney/common")):
                if(os.path.islink("../forge/mcp/src/minecraft/net/medsouz/miney/updater")):
                        os.rmlink("../forge/mcp/src/minecraft/net/medsouz/miney/updater")
                else:
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
sys.stdout.flush()
if(sys.platform.startswith("linux") or sys.platform.startswith("darwin")):
	runNative("python install.py")
	runNative("mkdir ../forge/mcp/src/minecraft/net/medsouz/")
	runNative("mkdir ../forge/mcp/src/minecraft/net/medsouz/miney/")
	runNative("ln -s ../../../../../../../src/net/medsouz/miney/client ../forge/mcp/src/minecraft/net/medsouz/miney/client")
	runNative("ln -s ../../../../../../../src/net/medsouz/miney/common ../forge/mcp/src/minecraft/net/medsouz/miney/common")
	runNative("ln -s ../../../../../../../Miney-Updater-master/src/net/medsouz/miney/updater ../forge/mcp/src/minecraft/net/medsouz/miney/updater")
elif(sys.platform.startswith("win32")):
	runNative("install.cmd")
	runNative("mkdir ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\")
	runNative("mklink /j ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\client ..\\src\\net\\medsouz\\miney\\client")
	runNative("mklink /j ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\common ..\\src\\net\\medsouz\\miney\\common")
	runNative("mklink /j ..\\forge\\mcp\\src\\minecraft\\net\\medsouz\\miney\\updater ..\\Miney-Updater-master\\src\\net\\medsouz\\miney\\updater")
print"==================================="
print "Miney-Client has been set up successfully!"
print "You can now use the forge folder to work on Miney"
print "Thank you for your contributions!"
print "    <3 medsouz"
print"==================================="