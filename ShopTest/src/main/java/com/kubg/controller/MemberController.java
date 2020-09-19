package com.kubg.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kubg.dto.MemberDTO;
import com.kubg.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	// 회원 가입 get
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() throws Exception {
	 logger.info("get signup");
	}

	// 회원 가입 post
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberDTO memberDTO) throws Exception {
	 logger.info("post signup");
	 String inputPass = memberDTO.getUserpass();
	 String pass = passEncoder.encode(inputPass);
	 memberDTO.setUserpass(pass);
	 memberService.signup(memberDTO);
	 return "redirect:/";
	}
	// 로그인 get
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception {
		logger.info("get signin");
	}
	// 로그인 post
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String postSignin(MemberDTO memberDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("post signin");
		MemberDTO login = memberService.signin(memberDTO);
		HttpSession httpSession = request.getSession();
		boolean passMatch = passEncoder.matches(memberDTO.getUserpass(), login.getUserpass());
		if (login != null && passMatch) {
			httpSession.setAttribute("member", login);
		} else {
			httpSession.setAttribute("member", null);
			redirectAttributes.addFlashAttribute("msg", false);
			return "redirect:/member/signin";
		}
		return "redirect:/";
	}
	// 로그아웃 get
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession httpSession) throws Exception {
		logger.info("get logout");
		memberService.signout(httpSession);
		return "redirect:/";
	}
}
