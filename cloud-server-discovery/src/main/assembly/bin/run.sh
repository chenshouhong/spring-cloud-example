#!/bin/sh

#check JAVA_HOME & java
noJavaHome=false
# -z 字符串长度伪则为真
if [ -z "$JAVA_HOME" ] ; then
    noJavaHome=true
fi
# -e 如果文件存在则为真
if [ ! -e "$JAVA_HOME/bin/java" ] ; then
    noJavaHome=true
fi
if $noJavaHome ; then
    echo
    echo "Error: JAVA_HOME environment variable is not set."
    echo
    exit 1
fi

APP_NAME=intellih-service-trade

#export JAVA_HOME=/home/tao/Program/java/jdk1.8.0_60
export JRE_HOME=$JAVA_HOME/jre
PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH
CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar
export  PATH CLASSPATH

JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=Asia/Shanghai -Dapp=$APP_NAME"


#  获取 根路径
cd $(dirname $(readlink -f "$0"))
cd ..
ROOT_HOME=$(pwd)
echo "ROOT_HOME==>"$ROOT_HOME

LIBDIR=$ROOT_HOME/lib
export LIBDIR

CONFDIR=$ROOT_HOME/config
export CONFDIR

CLASSPATH=${CLASSPATH}:${CONFDIR}:${LIBDIR}

for cdir in ${ROOT_HOME}/lib/*.jar
do
 CLASSPATH=$CLASSPATH:$cdir
 export CLASSPATH
done


#SERVICE_DIR=/home/tao/Program/hellow-service
#SERVICE_NAME=edu-service-$APP_NAME
#JAR_NAME=$SERVICE_NAME\.jar

# PID 文件在根目录
PID=$APP_NAME\.pid
echo "PID=>"$ROOT_HOME/$PID

case "$1" in

    start)
        #nohup $JAVA_HOME/bin/java com.alibaba.dubbo.container.Main >/dev/null 2>&1 &
        nohup $JAVA_HOME/bin/java $JAVA_OPTS com.alibaba.dubbo.container.Main >$ROOT_HOME/stdout.log 2>&1 &
        echo $! > $ROOT_HOME/$PID
        echo "=== start $APP_NAME"
        ;;

    stop)
        kill `cat $ROOT_HOME/$PID`
        rm -rf $ROOT_HOME/$PID
        echo "=== stop $APP_NAME"

        sleep 5
						        P_ID=`cat $APP_NAME\.pid`
        if [ "$P_ID" == "" ]; then
            echo "=== $APP_NAME process not exists or stop success"
        else
            echo "=== $APP_NAME process pid is:$P_ID"
            echo "=== begin kill $APP_NAME process, pid is:$P_ID"
            kill -9 $P_ID
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "=== restart $APP_NAME"
        ;;

    *)
                $0 stop
        sleep 2
        $0 start
        ;;

esac
exit 0

