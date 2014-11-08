package pl.java.scalatech.config;

import java.util.Properties;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import pl.java.scalatech.config.job.EventJob;
import pl.java.scalatech.quartz.QuartzJobListener;

@Configuration
@Slf4j
public class QuartzConfig {

    @Autowired
    private ApplicationContext applicationContext;

   

    @Bean
    public JobDetailFactoryBean eventJobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(EventJob.class);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean eventTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("EventTrigger");
        cronTriggerFactoryBean.setJobDetail(eventJobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression("0/15 * * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setSchedulerName("quartzScheduler");
        quartzScheduler.setApplicationContext(applicationContext);

        // quartzScheduler.setConfigLocation(new ClassPathResource("quartz.properties"));
        quartzScheduler.setQuartzProperties(quartzProperties());
        quartzScheduler.setGlobalJobListeners(new QuartzJobListener());
        quartzScheduler.setJobDetails(new JobDetail[] { eventJobDetailFactoryBean().getObject() });
        quartzScheduler.setTriggers(new Trigger[] { eventTriggerFactoryBean().getObject() });
        quartzScheduler.setAutoStartup(true);
        quartzScheduler.setStartupDelay(1);
      
        return quartzScheduler;
    }

    public Properties quartzProperties() {

        Properties props = new Properties();
        props.put("org.quartz.scheduler.instanceName", "przodownikScheduler");
        props.put("org.quartz.threadPool.threadCount", "3");
        props.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        return props;
    }
}