package com.kuebiko.it.config;

import static com.kuebiko.it.util.QuartzUtils.jobDetail;
import static com.kuebiko.it.util.QuartzUtils.trigger;

import com.kuebiko.it.job.ProductInsightJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

  private static final String EVERY_THIRTY_SECONDS = "0/30 * * * * ?";

  @Bean
  public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    scheduleInsightJob(scheduler);
    scheduler.start();

    return scheduler;
  }

  private void scheduleInsightJob(Scheduler scheduler) throws SchedulerException {
    scheduler.scheduleJob(
        jobDetail(ProductInsightJob.class, "insightJob", "insight"),
        trigger("insightTrigger", "insight", EVERY_THIRTY_SECONDS));
  }
}
