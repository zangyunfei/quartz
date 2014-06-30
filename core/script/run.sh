#/bin/bash

do=$1
#LANG="zh_CN.GB18030"
#export LANG

JAVA_HOME=/usr/java/default/
LD_LIBRARY_PATH=$LD_LIBRARY_PATH

echo $0
cd `dirname $0`/..
tmp_cur_path=`pwd`
cd $tmp_cur_path
echo $tmp_cur_path

start()
{
	echo "------start xjb service------"`date`
	$tmp_cur_path/script/run_xjb.sh start
	sleep 2
	echo "------start xjb task------"`date`
	$tmp_cur_path/script/run_task.sh start
	sleep 2
}

stop()
{
	echo "------stop xjb service------"`date`
	$tmp_cur_path/script/run_xjb.sh stop
	sleep 5
	echo "------stop xjb task------"`date`
	$tmp_cur_path/script/run_task.sh stop
	sleep 5
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
*)
	echo
	echo
        echo "Usage: $0 {start | stop }"
        ;;
esac
exit 0