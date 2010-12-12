mkdir bld
cd bld
mkdir idlpp
cd idlpp

#
# Generate java classes from IDL
#
/home/jadianes/PrismTech/OpenSpliceDDS/V5.2.0/HDE/x86.linux2.6/bin/idlpp -S -l java ../../MoteCloud.idl

#
# Compile generated java code
#
echo "Compile generated Java classes"
/home/jadianes/jrts-2.2_fcs/bin/javac -classpath .:$OSPL_HOME/jar/dcpssaj.jar -d .. MoteCloud/*.java

