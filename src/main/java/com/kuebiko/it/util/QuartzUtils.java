package com.kuebiko.it.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class QuartzUtils {

  private QuartzUtils() {
    throw new IllegalStateException("Util only, non intended for instantiation");
  }

  public static JobDetail jobDetail(Class<? extends Job> clazz, String name, String group) {
    return JobBuilder.newJob(clazz).withIdentity(name, group).build();
  }

  public static Trigger trigger(String name, String group, String cronExpression) {
    return TriggerBuilder.newTrigger()
        .withIdentity(name, group)
        .withSchedule(cronSchedule(cronExpression))
        .build();
  }
}
