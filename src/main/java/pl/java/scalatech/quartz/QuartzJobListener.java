package pl.java.scalatech.quartz;

import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.util.StringUtils;

@Slf4j
public class QuartzJobListener implements JobListener {

    @Override
    public String getName() {
        return "JobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        log.info("Job : " + jobName + " going to execute.");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        log.info("Job : " + jobName + " is vetoed...");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String jobName = context.getJobDetail().getKey().toString();
        log.info("Job : " + jobName + " is was exexuted...");
        if (jobException != null) {
            if (StringUtils.hasText(jobException.getMessage())) {
                log.warn("Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
            }
        }
    }
}