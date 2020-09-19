package com.kubg.service;

import javax.servlet.http.HttpSession;

import com.kubg.dto.MemberDTO;

public interface MemberService {
	// 회원가입
	public void signup(MemberDTO memberDTO) throws Exception;
	// 로그인
	public MemberDTO signin(MemberDTO memberDTO) throws Exception;
	// 로그아웃
	public void signout(HttpSession httpSession) throws Exception;
	
}
