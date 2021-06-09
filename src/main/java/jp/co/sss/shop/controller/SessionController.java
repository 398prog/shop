package jp.co.sss.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.form.LoginForm;
import jp.co.sss.shop.form.LoginFormWithAnnotation;
import jp.co.sss.shop.form.LoginFormWithValidation;

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

	@RequestMapping(path = "/loginOnRequest",method = RequestMethod.GET)
	public String loginOnRequest() {
		return "session/loginOnRequest";
	}

	@RequestMapping(path = "/doLoginOnRequest",method = RequestMethod.POST)
	public String doLoginOnRequest(LoginForm form,Model model) {
		model.addAttribute("userId",form.getUserId());
		return "session/loginOnRequest";
	}

	@RequestMapping(path = "/loginOnSession",method = RequestMethod.GET)
	public String loginOnSession() {
		return "session/loginOnSession";
	}

	@RequestMapping(path = "/doLoginOnSession",method = RequestMethod.POST)
	public String doLoginOnSession(LoginForm form, HttpSession session) {
		if (form.getUserId() == 123) {
			//入力したユーザ ID をスコープ変数 userId に代入し、
			//その変数をセッションに登録
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/loginOnSession";
		}

	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
	//セッションの破棄
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(path = "/loginWithValidation", method = RequestMethod.GET)
	public String loginWithValidation(@ModelAttribute LoginFormWithValidation form) {
	return "session/loginWithValidation";
	}

	@RequestMapping(path = "/loginWithValidation", method = RequestMethod.POST)
	public String doLoginWithValidation(@Valid @ModelAttribute LoginFormWithValidation form, BindingResult result,HttpSession session) {

		if (result.hasErrors()) {
			return loginWithValidation(form);
		}

		if (form.getUserId() == 123) {
			//入力したユーザ ID をスコープ変数「userId」に代入し、
			//その変数をセッションに登録
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/loginWithValidation";
		}

	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.GET)
	public String loginWithAnnotation(@ModelAttribute LoginFormWithAnnotation form) {
		return "session/loginWithAnnotation";
	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.POST)
	public String doLoginWithAnnotation(@Valid @ModelAttribute LoginFormWithAnnotation form,
	BindingResult result,HttpSession session) {

		if (result.hasErrors()) {
			return loginWithAnnotation(form);
		}
		if (form.getUserId() == 123) {

			//入力したユーザ ID をスコープ変数「userId」に代入し、
			//その変数をセッションに登録
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/loginWithAnnotation";
		}

	}



}