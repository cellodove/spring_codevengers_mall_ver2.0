package com.kubg.dao;

import com.kubg.dto.MemberDTO;

public interface MemberDAO {
	// 회원가입
	public void signup(MemberDTO memberDTO) throws Exception;
	// 로그인
	public MemberDTO signin(MemberDTO memberDTO) throws Exception;

}
