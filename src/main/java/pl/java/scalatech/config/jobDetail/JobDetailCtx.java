package pl.java.scalatech.config.jobDetail;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import pl.java.scalatech.config.job.FirstTask;

import com.google.common.collect.Maps;

@Configuration
public class JobDetailCtx {
    @Autowired
    private FirstTask runMeTask;

    @Bean
    public JobDetailFactoryBean runMeJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MyQuartzJobBean.class);
        Map<String, Object> map = Maps.newHashMap();
        map.put("runMeTask", runMeTask);
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }
}