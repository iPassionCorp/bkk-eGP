package com.skilledmonster.spring.integration.rss;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import com.rometools.rome.feed.synd.SyndEntry;

public class WebSyndicationInbound {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/rss-inbound-sample.xml");
 
        // create a pollable channel
        PollableChannel feedChannel = context.getBean("feedChannel", PollableChannel.class);
 
        for (int i = 0; i < 10; i++) {
            // receive the message feed
            Message<SyndEntry> message = (Message<SyndEntry>) feedChannel.receive(1000);
            if (message != null) {
                SyndEntry entry = message.getPayload();
                System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
            } else {
                break;
            }
        }
	}

}
