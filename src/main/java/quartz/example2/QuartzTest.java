package quartz.example2;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Quartz 使用JobData
 *
 * @author LDDFY
 */
public class QuartzTest {

    @Slf4j
    public static class QuartzJob implements Job {
        public static final String KEY = "id";
        public static final String SCORE = "score";

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobKey jobKey = context.getJobDetail().getKey();
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String id = dataMap.getString(KEY);
            float score = dataMap.getFloat(SCORE);
            log.info("Instance:{},id:{},score:{}", jobKey, id, score);
        }
    }


    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail jobDetail = newJob(QuartzJob.class)
                    .withIdentity("myJob", "myGroup")
                    .usingJobData(QuartzJob.KEY, "jobKey")
                    .usingJobData(QuartzJob.SCORE, 1.23f)
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("myTrigger", "myGroup")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(3)
                            .withRepeatCount(10))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            TimeUnit.SECONDS.sleep(32);
            scheduler.shutdown(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
