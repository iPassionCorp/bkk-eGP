package th.co.ipassion.bkkegp.model;

import java.sql.Date;

public class RssEgp {
	
	private long id;
	private Date publish_date;
	private String title;
	private String egp_url;

	public RssEgp() { super(); }
	
	public RssEgp (long id, Date publish_date, String title, String egp_url) {
		this.id = id;
		this.publish_date = publish_date;
		this.title = title;
		this.egp_url = egp_url;		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEgp_url() {
		return egp_url;
	}
	public void setEgp_url(String egp_url) {
		this.egp_url = egp_url;
	}
}