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
    	DemoFeedApplication demo = new DemoFeedApplication();
    	demo.feed("rss-egp-bkk.xml", "articleChannel", "*", "D0", "*");   	
    	demo.feed("rss-egp-bkk-demo.xml", "demoChannel", "*", "P0", "16");
    }
    
    public void feed (String xmlFileName, String channel, String deptSubId, String announceType, String methodId) throws InterruptedException, DAOException {   	
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlFileName);
        try {
           PollableChannel feedChannel = context.getBean(channel, PollableChannel.class);
            for (int i = 0; i < 20; i++) {
                Message message = (Message) feedChannel.receive(10000);
                if (message != null){
                    SyndEntry entry = (SyndEntry)message.getPayload();
                    System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
                    
                    RssEgp rss = new RssEgp();
                    rss.setTitle(entry.getTitle());
                    rss.setEgp_url(entry.getLink());
                                        
                    java.util.Calendar cal = Calendar.getInstance();
                    java.util.Date publishedDate = entry.getPublishedDate();
                    cal.setTime(publishedDate);
                    rss.setPublish_date(new java.sql.Date(cal.getTime().getTime()));                    
                    
                    rss.setDeptid(BMAConstant.DEPTID_BKK);
                    rss.setDeptsubid(deptSubId);
                    rss.setAnouncetype(announceType);
                    rss.setMethodid(methodId);
                                        
                    RssEgpDao service = new RssEgpDao();
                    service.create(rss);                   
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
