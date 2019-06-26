/*
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019/6/25，所有权利保留。
 *
 * 项目名：	quartz
 * 文件名：	QuartzTest
 * 模块说明：
 * 修改历史：
 *  2019年-06月-25日  - changhao - 创建。
 */
package quartz.example1;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * quartz 示例1
 * 
 * @author changhao
 */
public class QuartzTest {
  public static void main(String[] args) throws SchedulerException, InterruptedException {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    TimeUnit.SECONDS.sleep(10);
    scheduler.shutdown(true);
  }
}
