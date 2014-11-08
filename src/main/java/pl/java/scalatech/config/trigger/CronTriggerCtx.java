package pl.java.scalatech.config.trigger;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

@Configuration
public class CronTriggerCtx {
    @Autowired
    private JobDetail runMeJob;

    @Bean
    public CronTriggerFactoryBean quartzCronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(runMeJob);
        bean.setCronExpression("0/2 * * * * ?");
        return bean;
    }
}