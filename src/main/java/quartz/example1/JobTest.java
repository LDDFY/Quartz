package quartz.example1;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Job 测试
 *
 * @author LDDFY
 */
public class JobTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail detail = newJob(HelloJob.class)
                    .withIdentity("hello", "group1")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .withRepeatCount(10))
                    .build();
            scheduler.scheduleJob(detail, trigger);

            scheduler.start();
            //scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
