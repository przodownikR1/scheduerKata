package pl.java.scalatech.config.jobDetail;

import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import pl.java.scalatech.config.job.FirstTask;

@Service
@Slf4j
public class MyQuartzJobBean extends QuartzJobBean {

    private FirstTask firstTask;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("context:" + context);
        firstTask.execute();
    }

}