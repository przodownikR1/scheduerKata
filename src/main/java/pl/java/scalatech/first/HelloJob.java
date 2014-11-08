package pl.java.scalatech.first;

import lombok.extern.slf4j.Slf4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

@Slf4j
public class HelloJob implements Job {
    public HelloJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Hello! HelloJob is executing.");
        log.info(" fire :  {},  nextFire : {} , trigger : {}  ",context.getFireTime() , context.getNextFireTime());
        log.info("Trigger : {}",context.getTrigger());
        log.info("FireInstanceId : {}",context.getFireInstanceId());
   
        try {
            log.info("Scheduler TriggerGroupNames : {}",context.getScheduler().getTriggerGroupNames());
            log.info("Scheduler JobGroupNames : {}",context.getScheduler().getJobGroupNames());
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}