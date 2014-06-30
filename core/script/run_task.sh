#/bin/bash

do=$1
#LANG="zh_CN.GB18030"
#export LANG

JAVA_HOME=/usr/java/default/
LD_LIBRARY_PATH=$LD_LIBRARY_PATH

tmp_cur_path=`pwd`
cd `dirname $0`/..
APP_HOME=`pwd`
cd $tmp_cur_path


rm -fr $APP_HOME/lib/kcbpCliJni.jar
rm -fr $APP_HOME/lib/kjax-kcbp.jar
rm -fr $APP_HOME/build/*.dll



LOG_HOME=$APP_HOME/logs
APP_NAME=xjbtask
APP_MAINCLASS=com.xld.xjb.service.boostrap.TaskMain


JAVA_OPTS='-Djava.rmi.server.hostname=localhost
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=false
-Xms1024M -Xmx1024M -Xmn512M -XX:PermSize=256M -XX:MaxPermSize=256M -Xss256k 
-XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=7
-XX:GCTimeRatio=19 -XX:MaxDirectMemorySize=2048M
-XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
-XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime
-Xnoclassgc -Xloggc:logs/gc_task.log -XX:+DisableExplicitGC
-XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+UseCMSCompactAtFullCollection
-XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled
-XX:-CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=70
-XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintClassHistogram
-XX:+PrintConcurrentLocks '


LIB=$APP_HOME/lib
CLASSPATH=$APP_HOME/build
for i in "$APP_HOME"/lib/*.jar; do
  CLASSPATH="$CLASSPATH":"$i"
done

cd $APP_HOME/build/

STAT_PID=$APP_HOME/$APP_NAME.pid 

start()
{
	if test -e $STAT_PID
	then
		echo
		echo The $APP_NAME already Started!
		echo
	else
		echo
		echo Start The $APP_NAME ....
		echo
		$JAVA_HOME/bin/java -d64 -classpath $CLASSPATH $APP_MAINCLASS 1>>$LOG_HOME/stdout_$APP_NAME.log 2>>$LOG_HOME/stderr_$APP_NAME.log &
		#$JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAINCLASS 1>>$LOG_HOME/stdout_$APP_NAME.log 2>>$LOG_HOME/stderr_$APP_NAME.log &
		echo $!
		echo $!>$STAT_PID
		sleep 2
		STATUS=`ps -p $!|grep java |awk '{print $1}'`
		if test $STATUS
		        then
		                echo The $APP_NAME Started!
		                echo
		        else
		                echo The $APP_NAME Start Failed
		                echo please Check the system
		                echo
		fi
	fi
}
stop()
{
	if test -e $STAT_PID
	then
		echo
		echo Stop $APP_NAME....
		echo
		TPID=`cat $STAT_PID`
		kill $TPID
		sleep 1
		STATUS=`ps -p $TPID |grep java | awk '{print $1}'`
		if test $STATUS
		        then
		                echo $APP_NAME NOT Stoped!
		                echo please Check the system
		                echo
		        else
		                echo The $APP_NAME Stoped
		                echo
				rm $STAT_PID
		fi
	else
		echo
		echo $APP_NAME already Stoped!
		echo
	fi
}
status()
{
	if test -e $STAT_PID
	then
		TPID=`cat $STAT_PID`
		STATUS=`ps -p $TPID|grep java | awk '{print $1}'`
		if test $STATUS
		        then
		                echo "The $APP_NAME Running($TPID)!"
		                echo
		        else
		                echo The $APP_NAME NOT Running!
		                rm $STAT_PID
		                echo
		fi
	else
		echo
		echo The $APP_NAME NOT Running!
		echo
	fi
}

case "$1" in
'start')
		start
	;;
'stop')
		stop
	;;
'restart')
		stop
		start
	;;
'status')
		status
	;;
*)
	echo
	echo
        echo "Usage: $0 {status | start | stop }"
        echo
        echo Status of $APP_NAME ......
		status
        ;;
esac
exit 0
