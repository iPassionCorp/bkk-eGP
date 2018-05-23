package th.co.ipassion.bkkegp.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.ipassion.bkkegp.model.DataJson;

@RestController
public class JsonController {
	@RequestMapping("/testJson")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public DataJson testJson(
			@RequestParam(value = "deptId", defaultValue = "3100001") String deptId,
			@RequestParam(value = "param1", defaultValue = "pamram1")  String param1,
			@RequestBody DataJson input) {
		DataJson output = new DataJson();
		output.setKey("deptId : " + deptId + " | key : " + input.getKey());
		output.setValue("param1 : " + param1 + " | value_json : " + input.getValue());
		return output;
	}
}
