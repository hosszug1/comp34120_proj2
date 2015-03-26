if [ $(pidof rmiregistry) ]                                                     
then                                                                            
    echo "RMI already registry running!"
else                                                                            
    rmiregistry &                             
    echo "RMI started!"
fi   

set -f
CLASSPATH='-cp classes/:lib/*'
OPTIONS=-Djava.rmi.server.hostname=127.0.0.1

java $CLASSPATH $OPTIONS comp34120.ex2.Main &
set +f
sleep 2
rm -rf classes/*
javac -d classes/ src/*
java $CLASSPATH $OPTIONS SimpleLeader
