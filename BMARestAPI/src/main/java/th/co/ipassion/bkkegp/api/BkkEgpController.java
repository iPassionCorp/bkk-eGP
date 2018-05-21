package th.co.ipassion.bkkegp.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.ipassion.bkkegp.BMAConstant;
import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.dao.RssEgpDao;
import th.co.ipassion.bkkegp.model.RssEgp;

@RestController
public class BkkEgpController {  

    @RequestMapping("/listBkkEgp")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<RssEgp> listBkkEgp(
    		@RequestParam(value="deptId", defaultValue=BMAConstant.DEPTID_BKK) String deptId, 
    		@RequestParam(value="announceType", defaultValue="*") String announceType,
    		@RequestParam(value="methodId", defaultValue="*") String methodId
    		) {
    	RssEgpDao service = new RssEgpDao();
    	List<RssEgp> result = null;
    	try { 
    		if (deptId.equalsIgnoreCase(BMAConstant.DEPTID_BKK) 
    				&& "*".equalsIgnoreCase(announceType)
    				&& "*".equalsIgnoreCase(methodId)) {
    			
    			result = service.getAllEgpInfo();
    			
    		} else if (!deptId.equalsIgnoreCase(BMAConstant.DEPTID_BKK) 
    				&& "*".equalsIgnoreCase(announceType)
    				&& "*".equalsIgnoreCase(methodId)) {
    			
    			result = service.getEgpInfoBySubDeptCodeId(deptId);
    			
    		} else {
    			
    			result = service.getEgpInfoByCondition(deptId, announceType, methodId);
    			
    		}
    		
    		
    	} catch (DAOException e) { e.printStackTrace(); }
    	return result;
    }
}
