package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(path="/")
	public String index() {
		return "index";
	}

	@RequestMapping("/before")
	public String before() {
		return "before";
	}

	@RequestMapping("/after")
	public String after() {
		return "after";
	}

	@RequestMapping("/forward_redirect")
	public String forward_redirect(){
		return "forward_redirect";
	}

	@RequestMapping("/index_forward")
	public String index_forward() {
		return "index";
	}

	@RequestMapping("/index_redirect")
	public String index_redirect() {
		return "redirect:/";
	}

	@RequestMapping("/layout_view")
	public String layout_view() {
		return "layout_view";
	}


}
