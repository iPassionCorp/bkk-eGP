package com.javarticles.spring.integration.feed;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import com.rometools.rome.feed.synd.SyndEntry;

public class SpringIntegrationPollableChannelFeedExample {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "pollableChannelFeedContext.xml");
        try {
           PollableChannel feedChannel = context.getBean("articleChannel", PollableChannel.class);
            for (int i = 0; i < 10; i++) {
                Message message = (Message) feedChannel.receive(10000);
                if (message != null){
                    SyndEntry entry = (SyndEntry)message.getPayload();
                    System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
                }
                else {
                    break;
                }
            }            
        } finally {
            context.close();
        }
    }
}
