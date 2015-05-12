package vivian.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class ThreadPoolTest {
	public static void main(String[] args) {
		Scheduler s1;
		for (int i = 0; i < 10; i++) {
			Date runTime = new Date();
			System.out.print(runTime);

			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(HelloJob.class).withIdentity("job" + i,"group1").build();

			Trigger trigger = newTrigger().withIdentity("trigger" + i, "group1").startAt(runTime)
					.withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

			// Tell quartz to schedule the job using our trigger
			try {
				s1 = new StdSchedulerFactory().getScheduler();
				s1.scheduleJob(job, trigger);
				s1.start();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}
	
	class HelloJob implements org.quartz.Job{
		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException{
			System.out.println("Hello World! - @@@@@"+context.getJobDetail() );
		}
	}
}
