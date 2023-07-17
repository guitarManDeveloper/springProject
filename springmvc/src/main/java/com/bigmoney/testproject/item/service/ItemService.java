package com.bigmoney.testproject.item.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bigmoney.testproject.item.vo.ItemVO;

public interface ItemService {
	
	void insert(ItemVO itemVO, HttpServletRequest request)throws Exception;
	
	/**
	 * 상품 상세정보 조회
	 * @param map
	 * @return
	 */
	ItemVO detail(ItemVO itemVO);

	boolean update(Map<String, Object> map);
	void delete(Map<String, Object> map);
	ItemVO selectItemList(ItemVO itemVO);

	/**
	 * 상품목록의 카운트 수
	 * @param map
	 * @return
	 */
	int getcount(ItemVO itemVO);

	void hitUp(ItemVO itemVO);
}
