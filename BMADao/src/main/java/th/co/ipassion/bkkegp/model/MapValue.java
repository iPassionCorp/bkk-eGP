package th.co.ipassion.bkkegp.model;

public class MapValue {
	String param;
	String description;
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public MapValue(String param, String description) {
		super();
		this.param = param;
		this.description = description;
	}
	
	
}
