package com.bigmoney.testproject.service;

import java.util.List;
import java.util.Map;

public interface ItemService {
	String insert(Map<String, Object> map);
	List<Map<String, Object>> selectAll();

	/**
	 * 상품 상세정보 조회
	 * @param map
	 * @return
	 */
	Map<String, Object> detail(Map<String, Object> map);

	boolean update(Map<String, Object> map);
	void delete(Map<String, Object> map);
	List<Map<String, Object>> selectItemList(Map<String, Object> map);

	/**
	 * 상품목록의 카운트 수
	 * @param map
	 * @return
	 */
	int getcount(Map<String, Object> map);

	void hitUp(Map<String, Object> detailMap);
}
