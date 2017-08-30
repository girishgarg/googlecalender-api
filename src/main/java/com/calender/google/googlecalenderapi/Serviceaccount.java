package com.calender.google.googlecalenderapi;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;

import com.google.api.services.calendar.model.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Serviceaccount {
	
	/** Global instance of the HTTP transport. */
	  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	  public static void main(String[] args) {
	    try {
	        
	    	/*GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("C:\Users\girish\workspace4\googlecalenderapi\src\main\resources/My_Project.json"))
	    		    .createScoped(Collections.singleton(CalendarScopes.CALENDAR));*/
	    	
	    	GoogleCredential credential = 
	                new GoogleCredential.Builder()
	                .setTransport(HTTP_TRANSPORT)
	                  .setJsonFactory(JSON_FACTORY)
	                  .setServiceAccountId("calendar@acquired-vector-171610.iam.gserviceaccount.com")
	                  .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
	                  .setServiceAccountPrivateKeyFromP12File(new File("C:/Users/girish/workspace4/googlecalenderapi/src/main/resources/my_project.p12"))
	                  .setServiceAccountUser("mohit@eze.ai")
	                  .build();
	        System.out.println(credential.getAccessToken());
	        //password for .p12 file is "notasecret".
	        com.google.api.services.calendar.Calendar client = new com.google.api.services.calendar.Calendar.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName( "Google Calendar Sync").build();
	      
	        
	        //create an event
	        Event event = new Event()
	        	    .setSummary("Google I/O 2015")
	        	    .setLocation("800 Howard St., San Francisco, CA 94103")
	        	    .setDescription("A chance to hear more about Google's developer products.");

	        	DateTime startDateTime = new DateTime("2017-09-24T09:00:00-07:00");
	        	EventDateTime start = new EventDateTime()
	        	    .setDateTime(startDateTime)
	        	    .setTimeZone("America/Los_Angeles");
	        	event.setStart(start);

	        	DateTime endDateTime = new DateTime("2017-09-25T17:00:00-07:00");
	        	EventDateTime end = new EventDateTime()
	        	    .setDateTime(endDateTime)
	        	    .setTimeZone("America/Los_Angeles");
	        	event.setEnd(end);

	        	String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
	        	event.setRecurrence(Arrays.asList(recurrence));

	        	EventAttendee[] attendees = new EventAttendee[] {
	        	    new EventAttendee().setEmail("sushant@eze.ai"),
	        	    //new EventAttendee().setEmail("sbrin@example.com"),
	        	};
	        	event.setAttendees(Arrays.asList(attendees));

	        	EventReminder[] reminderOverrides = new EventReminder[] {
	        	    new EventReminder().setMethod("email").setMinutes(24 * 60),
	        	    new EventReminder().setMethod("popup").setMinutes(10),
	        	};
	        	Event.Reminders reminders = new Event.Reminders()
	        	    .setUseDefault(false)
	        	    .setOverrides(Arrays.asList(reminderOverrides));
	        	event.setReminders(reminders);

	        	String calendarId = "primary";
	        	event = client.events().insert(calendarId, event).execute();
	        	System.out.printf("Event created: %s\n", event.getHtmlLink());
	        // List the next 10 events from the primary calendar.
	        DateTime now = new DateTime(System.currentTimeMillis());
	        Events events = client.events().list("mohit@eze.ai")
	            .setMaxResults(10)
	            .setTimeMin(now)
	            .setOrderBy("startTime")
	            .setSingleEvents(true)
	            .execute();
	        List<Event> items = events.getItems();
	        if (items.size() == 0) {
	            System.out.println("No upcoming events found.");
	        } else {
	            System.out.println("Upcoming events");
	            for (Event event1 : items) {
	                DateTime start1 = event1.getStart().getDateTime();
	                if (start1 == null) {
	                    start1 = event1.getStart().getDate();
	                }
	                System.out.printf("%s (%s)\n", event1.getSummary(), start1);
	            }
	        }
	  } catch (Exception e) {
	     e.printStackTrace();
	  }
	  }
}
