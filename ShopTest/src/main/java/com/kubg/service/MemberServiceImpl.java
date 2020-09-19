package com.kubg.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.kubg.dao.MemberDAO;
import com.kubg.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO memberDAO;
	
	// 회원가입
	@Override
	public void signup(MemberDTO memberDTO) throws Exception {
		memberDAO.signup(memberDTO);
	}
	// 로그인
	@Override
	public MemberDTO signin(MemberDTO memberDTO) throws Exception {
		return memberDAO.signin(memberDTO);
	}
	// 로그아웃
	@Override
	public void signout(HttpSession httpSession) throws Exception {
		httpSession.invalidate();
	}

}
