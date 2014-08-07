package com.xjb.xld.monitor.boostrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;

import com.xjb.xld.monitor.common.context.QuartzServiceContext;

/**
 * 启动执行task任务
 * 
 * @author peng.liu
 * 
 */
public class PayTaskMain {
	static Log log = LogFactory.getLog(PayTaskMain.class);
	private static ApplicationContext context;

	/**
	 * 启动类
	 * 
	 * @param args
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws SchedulerException {
		initContext();
		try {
			StdScheduler scheduler = (StdScheduler) context
					.getBean("quartzScheduler");
			String[] triggerNames = scheduler
					.getTriggerNames(Scheduler.DEFAULT_GROUP);
			String[] configTriggerNames = context
					.getBeanNamesForType(org.springframework.scheduling.quartz.CronTriggerBean.class);
			List<String> pauseTriggerList = new ArrayList<String>();
			pauseTriggerList.addAll(Arrays.asList(triggerNames));
			for (String configTriggerName : configTriggerNames) {
				pauseTriggerList.remove(configTriggerName);
			}
			for (String triggerName : pauseTriggerList) {
				scheduler.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);
			}
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initContext() {
		if (context == null) {
			context = QuartzServiceContext.getContext();
		}
	}
}
