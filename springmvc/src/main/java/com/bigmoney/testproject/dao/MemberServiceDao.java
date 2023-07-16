package com.bigmoney.testproject.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberServiceDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public Map<String, Object> getMember(Map<String, Object> map) {
		Map<String, Object> result =  sqlSessionTemplate.selectOne("member.getmember", map);		
		return result;
	}

}
