package com.bigmoney.testproject.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public List<Map<String, Object>> getCode() {
		return sst.selectList("common.select");
		
	}

}
