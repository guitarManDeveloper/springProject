package com.bigmoney.testproject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDao {
	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	public int insert(Map<String, Object> map) {
		
		return sqlsessionTemplate.insert("item.insert", map);
	}

	public List<Map<String, Object>> selectAll() {		
		return sqlsessionTemplate.selectList("item.getItem");
	}

	public Map<String, Object> detail(Map<String, Object> map) {		
		return sqlsessionTemplate.selectOne("item.select_detail", map);
	}

	public boolean update(Map<String, Object> map) {		
		int result =  sqlsessionTemplate.update("item.modify", map);
		return result == 1;
		
	}

	public void delete(Map<String, Object> map) {
		sqlsessionTemplate.delete("item.delete", map);		
	}

	public List<Map<String, Object>> selectItemList(Map<String, Object> map) {
		return sqlsessionTemplate.selectList("item.selectItemList", map);
	}

	public int getcount(Map<String, Object> map) {
		return sqlsessionTemplate.selectOne("item.getcount", map);
	}

    public void hitUp(Map<String, Object> detailMap) {
		sqlsessionTemplate.update("item.hitUp",detailMap);
    }
}
