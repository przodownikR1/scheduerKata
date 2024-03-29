package pl.java.scalatech.config.job;

import lombok.extern.slf4j.Slf4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
          log.info("Event job {}",context.getFireTime());
    }
}