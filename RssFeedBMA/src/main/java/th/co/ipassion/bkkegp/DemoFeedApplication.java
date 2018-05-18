package th.co.ipassion.bkkegp;

import java.util.Calendar;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import com.rometools.rome.feed.synd.SyndEntry;

import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.dao.RssEgpDao;
import th.co.ipassion.bkkegp.model.RssEgp;

public class DemoFeedApplication {
    public static void main(String[] args) throws InterruptedException, DAOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "rss-egp-bkk.xml");
        try {
           PollableChannel feedChannel = context.getBean("articleChannel", PollableChannel.class);
            for (int i = 0; i < 20; i++) {
                Message message = (Message) feedChannel.receive(10000);
                if (message != null){
                    SyndEntry entry = (SyndEntry)message.getPayload();
                    System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
                    System.out.println(entry.getLink());
                    
                    RssEgp rss = new RssEgp();
                    rss.setTitle(entry.getTitle());
                    rss.setEgp_url(entry.getLink());
                                        
                    java.util.Calendar cal = Calendar.getInstance();
                    java.util.Date publishedDate = entry.getPublishedDate();
                    cal.setTime(publishedDate);
                    rss.setPublish_date(new java.sql.Date(cal.getTime().getTime()));                    
                    
                    System.out.println("Start Saving this record into Database...");
                    
                    RssEgpDao service = new RssEgpDao();
                    service.create(rss); 
                    
                    System.out.println("Finish Saved this record into Database");
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
