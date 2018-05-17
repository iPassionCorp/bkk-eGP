package th.co.ipassion.bkkegp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import com.rometools.rome.feed.synd.SyndEntry;

public class DemoFeedApplication {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "rss-egp-bkk.xml");
        try {
           PollableChannel feedChannel = context.getBean("articleChannel", PollableChannel.class);
            for (int i = 0; i < 5; i++) {
                Message message = (Message) feedChannel.receive(10000);
                if (message != null){
                    SyndEntry entry = (SyndEntry)message.getPayload();
                    System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
                    System.out.println(entry.getLink());
                    System.out.println(entry.toString());
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
