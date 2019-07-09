package quartz.example1;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Job 测试
 *
 * @author LDDFY
 */
public class JobTest {
  public static void main(String[] args) {
    try {
      // 获取调度器
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

      // 定义Job信息
      JobDetail detail = newJob(HelloJob.class).withIdentity("hello", "group1").build();

      // 定义触发器
      Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow()
          .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();

      // 添加Job到调度器中
      scheduler.scheduleJob(detail, trigger);
      // 启动调度器
      scheduler.start();
      // scheduler.shutdown(true);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}
