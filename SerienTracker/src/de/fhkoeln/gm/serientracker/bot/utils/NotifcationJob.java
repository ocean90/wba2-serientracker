package de.fhkoeln.gm.serientracker.bot.utils;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import de.fhkoeln.gm.serientracker.utils.Logger;

public class NotifcationJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String id = dataMap.getString("id");
        String users = dataMap.getString("users");

		Logger.log( String.format( "Notification Job: Key: %s ID: %s Users: %s", key.getName(), id, users ) );
    }

}
