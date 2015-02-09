package hk.org.ha.pbrc.dashboard.scheduler;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class CronScheduler extends HttpServlet {

    private static final long serialVersionUID = 1567185871113714035L;

    public void init(ServletConfig cfg) {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler sched;
        try {
            // define the job and tie it to our HelloJob class
            JobDetail end2endJob = JobBuilder.newJob(EndToEndMonitorJob.class).withIdentity("EndToEndMonitor", "FileDataSource").build();
            JobDetail reportMonitorJob = JobBuilder.newJob(ReportMonitorJob.class).withIdentity("ReportMonitor", "MonitorEmail").build();
            JobDetail pmiMonitorJob = JobBuilder.newJob(PmiMonitorJob.class).withIdentity("PmiMonitor", "FileDataSource").build();
            JobDetail workflowMonitorJob = JobBuilder.newJob(WorkflowMonitorJob.class).withIdentity("WorkflowMonitor", "FileDataSource").build();
            // Trigger the job to run now, and then every 5 mins
            Trigger end2endTrigger = TriggerBuilder.newTrigger().withIdentity("EndToEndMonitorTrigger", "FileDataSource").startNow().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
            Trigger reportMonitorTrigger = TriggerBuilder.newTrigger().withIdentity("ReportMonitorTrigger", "MonitorEmail").startNow().withSchedule(CronScheduleBuilder.cronSchedule("0 0 9am * * ?")).build();
            Trigger pmiMonitorTrigger = TriggerBuilder.newTrigger().withIdentity("PmiMonitorTrigger", "FileDataSource").startNow().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
            Trigger workflowMonitorTrigger = TriggerBuilder.newTrigger().withIdentity("WorkflowMonitorTrigger", "FileDataSource").startNow().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
           
            // Tell quartz to schedule the job using our trigger
            sched = factory.getScheduler();            
            sched.start();
            sched.scheduleJob(end2endJob, end2endTrigger);
            sched.scheduleJob(reportMonitorJob, reportMonitorTrigger);
            sched.scheduleJob(pmiMonitorJob, pmiMonitorTrigger);
            sched.scheduleJob(workflowMonitorJob, workflowMonitorTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
