package com.bigmoney.testproject.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigmoney.testproject.dao.MemberServiceDao;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberServiceDao memberServiceDao;  
	
	@Override
	public Map<String, Object> getMember(Map<String, Object> map) {
		return memberServiceDao.getMember(map);
	}

}
