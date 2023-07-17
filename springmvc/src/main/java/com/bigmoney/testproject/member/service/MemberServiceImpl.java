package com.bigmoney.testproject.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigmoney.testproject.member.dao.MemberServiceDao;
import com.bigmoney.testproject.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberServiceDao memberServiceDao;  
	
	@Override
	public MemberVO getMember(MemberVO memberVO) {
		return memberServiceDao.getMember(memberVO);
	}

}
