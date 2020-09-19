package com.kubg.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kubg.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	//회원가입
	@Override
	public void signup(MemberDTO memberDTO) throws Exception {
		sqlSession.insert("signup", memberDTO);
	}
	//로그인
	@Override
	public MemberDTO signin(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne("signin", memberDTO);
		
	}

}
