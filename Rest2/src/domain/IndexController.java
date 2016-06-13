package domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@RequestMapping(value="/index")
	public @ResponseBody Greeting sayHello(@RequestParam("name") String name){
		System.out.println("Hello world");
		return new Greeting(name);
	}
}
