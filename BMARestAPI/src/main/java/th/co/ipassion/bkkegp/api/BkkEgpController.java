package th.co.ipassion.bkkegp.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.dao.RssEgpDao;
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
    }}
