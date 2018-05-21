package th.co.ipassion.bkkegp.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.ipassion.bkkegp.dao.AnnounceTypeDao;
import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.dao.MethodIDTypeDao;
import th.co.ipassion.bkkegp.dao.RssEgpDao;
import th.co.ipassion.bkkegp.model.DataJson;
import th.co.ipassion.bkkegp.model.MapValue;
import th.co.ipassion.bkkegp.model.RssEgp;

@RestController
public class BkkEgpController {  

    @RequestMapping("/listBkkEgp")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<RssEgp> listBkkEgp(@RequestParam(value="deptId", defaultValue="3100001") String deptId) {
    	RssEgpDao service = new RssEgpDao();
    	List<RssEgp> result = null;
    	try { 
    		if (deptId.equalsIgnoreCase("3100001") || deptId.equalsIgnoreCase("*")) {
    			result = service.getAllEgpInfo();
    		} else {
    			result = service.getEgpInfoBySubDeptCodeId(deptId);
    		}
    	} catch (DAOException e) { e.printStackTrace(); }
    	return result;
    }
    

    @RequestMapping("/listAnnounce")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<MapValue> getListAnnounce(@RequestParam(value="deptId", defaultValue="3100001") String deptId) {
    	AnnounceTypeDao service = new AnnounceTypeDao();
    	List<MapValue> result = null;
    	try { 
    		result = service.getDataAll();
    	} catch (DAOException e) {
    		e.printStackTrace(); 
    	}
    	return result;
    }
    
    @RequestMapping("/listMethodId")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<MapValue> getListMethodId(@RequestParam(value="deptId", defaultValue="3100001") String deptId) {
    	MethodIDTypeDao service = new MethodIDTypeDao();
    	List<MapValue> result = null;
    	try { 
    		result = service.getDataAll();
    	} catch (DAOException e) {
    		e.printStackTrace(); 
    	}
    	return result;
    }
    
    @RequestMapping("/testJson")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public DataJson testJson(@RequestBody DataJson j) {
    	DataJson output = new DataJson();
    	output.setKey("key Json : " + j.getKey());
    	output.setValue("value_json : "  + j.getValue());
    	return output;
    }
}
