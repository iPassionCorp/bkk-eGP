package th.co.ipassion.bkkegp;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rometools.rome.feed.synd.SyndEntry;

import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.dao.RssEgpDao;
import th.co.ipassion.bkkegp.model.RssEgp;

public class DemoFeedApplication {
	public static void main(String[] args) throws InterruptedException, DAOException {
		DemoFeedApplication demo = new DemoFeedApplication();
		String fileName = "rss-egp-bkk-demo.xml";
		String keyElement = "int-feed:inbound-channel-adapter";
		// demo.feed("rss-egp-bkk.xml", "articleChannel", "*", "D0", "*");
		// demo.feed("rss-egp-bkk-demo.xml", "demoChannel", "*", "P0", "16");
<<<<<<< HEAD
		demo.feed_v2(fileName, keyElement);
	}

	private Document loadTestDocument(String url) throws Exception {
		Document ret = null;
		DocumentBuilderFactory domFactory;
		DocumentBuilder builder;
		try {
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(false);
			domFactory.setNamespaceAware(false);
			builder = domFactory.newDocumentBuilder();
			InputStream in = new URL(url).openStream();
			ret = builder.parse(in);
		} catch (Exception ex) {
			System.out.println("error");
			ex.printStackTrace();
=======
		// demo.feed("rss-egp-bkk-demo.xml", "demoChannel", "*", "P0", "16");
		demo.feed_v2(fileName, keyElement);
	}

	private Document loadTestDocument(String url) throws Exception {
		Document ret = null;
		DocumentBuilderFactory domFactory;
		DocumentBuilder builder;
		try {
			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setValidating(false);
			domFactory.setNamespaceAware(false);
			builder = domFactory.newDocumentBuilder();
			InputStream in = new URL(url).openStream();
			ret = builder.parse(in);
		} catch (Exception ex) {
			System.out.println("error");
>>>>>>> branch 'master' of https://github.com/iPassionCorp/bkk-eGP.git
		}
		return ret;
	}

	public void feed_v2(String xmlFileName, String keyElement) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(getClass().getClassLoader().getResourceAsStream(xmlFileName));
			NodeList nList = doc.getElementsByTagName(keyElement);
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				Element eElement = (Element) nNode;
				String url = eElement.getAttribute("url");
				String deptId = url.split("deptId=")[1].split("&")[0];
				String anounceType = url.split("anounceType=")[1].split("&")[0];
				String methodId = url.split("methodId=")[1].split("&")[0];
				System.out.println("+++++++++++++++++++++++++++++++++++");
				System.out.println("url : " + url);
				Document docInner = loadTestDocument(url);
				NodeList nListInner = docInner.getElementsByTagName("item");
				for (int iInner = 0; iInner < nListInner.getLength(); iInner++) {
					RssEgp rss = new RssEgp();
					Node nNodeInner = nListInner.item(iInner);
					Element e = (Element) nNodeInner;
					rss.setTitle(e.getElementsByTagName("title").item(0).getTextContent());
					rss.setEgp_url(e.getElementsByTagName("link").item(0).getTextContent());
					java.util.Calendar cal = Calendar.getInstance();
					cal.setTime(new java.sql.Date(cal.getTime().getTime()));
					rss.setPublish_date(new java.sql.Date(cal.getTime().getTime()));
					rss.setDeptid(BMAConstant.DEPTID_BKK);
					rss.setDeptsubid(deptId);
					rss.setAnouncetype(anounceType);
					rss.setMethodid(methodId);
					System.out.println(rss.getPublish_date() + " - " + rss.getTitle());
					RssEgpDao service = new RssEgpDao();
					service.create(rss);
				}
			}
		} catch (Exception e) {
			System.out.println("error getLinkFeed");
			e.printStackTrace();
		}
	}

	public void feed(String xmlFileName, String channel, String deptSubId, String announceType, String methodId)
			throws InterruptedException, DAOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlFileName);
		try {
			PollableChannel feedChannel = context.getBean(channel, PollableChannel.class);
			for (int i = 0; i < 20; i++) {
				Message message = (Message) feedChannel.receive(10000);
				if (message != null) {
					SyndEntry entry = (SyndEntry) message.getPayload();
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
				} else {
					break;
				}
			}
		} finally {
			context.close();
		}
	}

}
