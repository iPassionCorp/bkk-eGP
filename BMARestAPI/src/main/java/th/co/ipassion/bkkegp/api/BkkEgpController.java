package th.co.ipassion.bkkegp.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.dao.RssEgpDao;
import th.co.ipassion.bkkegp.model.RssEgp;

@RestController
public class BkkEgpController {

    @RequestMapping("/listBkkEgp")
    public List<RssEgp> listBkkEgp(@RequestParam(value="deptId", defaultValue="3100001") String deptId) {
    	RssEgpDao service = new RssEgpDao();
    	List<RssEgp> result = null;
    	
    	try {
    		result = service.getEgpInfo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
    	
    	return result;
    }
}