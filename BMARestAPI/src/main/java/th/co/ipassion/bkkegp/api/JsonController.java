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
			@RequestBody DataJson input) 
	{
		DataJson output = new DataJson();
		System.out.println("deptId : " + deptId + " | input.getKey() : " + input.getKey());
		System.out.println("input.getValue() : " + input.getValue());
		output.setKey("deptId : " + deptId + " | key : " + input.getKey());
		output.setValue("value_json : " + input.getValue());
		return output;
	}
}
