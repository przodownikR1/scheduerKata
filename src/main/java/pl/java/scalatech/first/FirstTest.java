package pl.java.scalatech.first;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

public class FirstTest {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();

        JobDetail job = newJob(HelloJob.class).withIdentity("myJob", "group1").build();

        Trigger trigger = newTrigger().withIdentity("myTrigger", "group1").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        sched.scheduleJob(job, trigger);
    }
}
