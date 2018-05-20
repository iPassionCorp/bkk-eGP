package th.co.ipassion.bkkegp.model;

import java.sql.Date;

public class RssEgp {
	
	private long id;
	private Date publish_date;
	private String title;
	private String egp_url;
	private String deptid;
	private String deptsubid;
	private String anouncetype;
	private String methodid;

	public RssEgp() { super(); }
	
	public RssEgp (long id, Date publish_date, String title, String egp_url) {
		this.id = id;
		this.publish_date = publish_date;
		this.title = title;
		this.egp_url = egp_url;
	}

	public RssEgp (long id, Date publish_date, String title, String egp_url, String deptid, String deptsubid, String anouncetype, String methodid) {
		this.id = id;
		this.publish_date = publish_date;
		this.title = title;
		this.egp_url = egp_url;
		this.deptid = deptid;
		this.deptsubid = deptsubid;
		this.anouncetype = anouncetype;
		this.methodid = methodid;
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
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptsubid() {
		return deptsubid;
	}
	public void setDeptsubid(String deptsubid) {
		this.deptsubid = deptsubid;
	}
	public String getAnouncetype() {
		return anouncetype;
	}
	public void setAnouncetype(String anouncetype) {
		this.anouncetype = anouncetype;
	}
	public String getMethodid() {
		return methodid;
	}
	public void setMethodid(String methodid) {
		this.methodid = methodid;
	}
	
}