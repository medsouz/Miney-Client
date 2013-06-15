cd `dirname $0`
cd scripts/
python setup.py
cd ../forge/mcp/
rm src/minecraft/net/medsouz/miney/updater
python runtime/recompile.py "$@"
rc=$?
if [[ $rc != 0 ]] ; then
    exit $rc
fi
python runtime/reobfuscate.py "$@"
rc=$?
if [[ $rc != 0 ]] ; then
    exit $rc
fi
echo "Miney has compiled successfully!";
