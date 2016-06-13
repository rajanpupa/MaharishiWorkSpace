package test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestApplication {

	@RequestMapping("/")
	public String helloSlash(HttpServletRequest request){
		System.out.println(request.getServletPath());
		
		return "index";
	}
	
	@RequestMapping(value="/index")
	public String index(){
		System.out.println("in the index method");
		return "index";
	}
	
	@RequestMapping(value="/intro")
	public ModelAndView intro(Model model){
		List<String> lines = new ArrayList<String>();
		lines.add("Controller creates an object and passes it to the view");
		lines.add("The view layer can use any technology and could display this message");
		
		model.addAttribute("lines", lines);
		
		return new ModelAndView("intro");
	}
}
