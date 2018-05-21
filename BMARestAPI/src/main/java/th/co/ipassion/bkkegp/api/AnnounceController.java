package th.co.ipassion.bkkegp.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.ipassion.bkkegp.dao.AnnounceTypeDao;
import th.co.ipassion.bkkegp.dao.DAOException;
import th.co.ipassion.bkkegp.model.MapValue;

@RestController
public class AnnounceController {
	@RequestMapping("/listAnnounce")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public List<MapValue> getListAnnounce(@RequestParam(value = "deptId", defaultValue = "3100001") String deptId) {
		AnnounceTypeDao service = new AnnounceTypeDao();
		List<MapValue> result = null;
		try {
			result = service.getDataAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
