package com.kubg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kubg.dto.MemberDTO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		HttpSession httpSession = request.getSession();
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		
		if (memberDTO == null) {
			response.sendRedirect("/member/signin");
			return false;
		}
		
		if (memberDTO.getVerify() != 9) {
			response.sendRedirect("/");
			return false;
		}
		return true;
	}
}
