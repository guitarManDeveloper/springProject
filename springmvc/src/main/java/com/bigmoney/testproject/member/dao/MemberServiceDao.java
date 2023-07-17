package com.bigmoney.testproject.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigmoney.testproject.member.vo.MemberVO;

@Repository
public class MemberServiceDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public MemberVO getMember(MemberVO memberVO) {
		MemberVO result =  sqlSessionTemplate.selectOne("member.getmember", memberVO);		
		return result;
	}

}
