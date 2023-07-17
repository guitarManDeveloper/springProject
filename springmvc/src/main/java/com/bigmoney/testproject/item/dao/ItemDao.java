package com.bigmoney.testproject.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigmoney.testproject.item.vo.ItemVO;

@Repository
public class ItemDao {
	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	public void insert(ItemVO itemVO) {
		sqlsessionTemplate.insert("item.insert", itemVO);
	}

	public ItemVO detail(ItemVO itemVO) {		
		return sqlsessionTemplate.selectOne("item.select_detail", itemVO);
	}

	public boolean update(Map<String, Object> map) {		
		int result =  sqlsessionTemplate.update("item.modify", map);
		return result == 1;
		
	}

	public void delete(Map<String, Object> map) {
		sqlsessionTemplate.delete("item.delete", map);		
	}

	public List<ItemVO> selectItemList(ItemVO itemVO) {
		return sqlsessionTemplate.selectList("item.selectItemList", itemVO);
	}

	public int getcount(ItemVO itemVO) {
		return sqlsessionTemplate.selectOne("item.getcount", itemVO);
	}

    public void hitUp(ItemVO itemVO) {
		sqlsessionTemplate.update("item.hitUp",itemVO);
    }
}
