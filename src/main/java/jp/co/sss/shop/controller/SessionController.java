package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.form.LoginForm;

@Controller
public class SessionController {
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "session/login";
	}
	@RequestMapping(path = "/doLogin", method = RequestMethod.GET)
	public String doLoginGet(String userId) {
		System.out.println("ユーザ ID:" + userId);
		return "session/login";
	}

//	@RequestMapping(path = "/doLogin", method = RequestMethod.POST)
//	public String doLoginPost(String userId) {
//		System.out.println("ユーザ ID:" + userId);
//		return "session/login";
//	}

	@RequestMapping(path = "/doLogin", method = RequestMethod.POST)
	public String doLoginPost(String userId, String password) {
		System.out.println("ユーザID："+userId);
		System.out.println("パスワード:"+password);
		return "session/login";
	}

	@RequestMapping(path = "/loginUsingForm",method = RequestMethod.GET)
	public String loginUsingForm() {
		return "session/loginUsingForm";
	}

	@RequestMapping(path = "/doLoginUsingForm",method = RequestMethod.POST)
	public String doLoginUsingForm(LoginForm form) {
		System.out.println("ユーザ ID:" + form.getUserId());
		System.out.println("パスワード:" + form.getPassword());
		return "session/loginUsingForm";
	}
}