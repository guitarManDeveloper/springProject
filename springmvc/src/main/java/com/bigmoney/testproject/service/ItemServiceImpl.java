package com.bigmoney.testproject.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigmoney.testproject.dao.ItemDao;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemDao itemDao;
	
	@Override
	public String insert(Map<String, Object> map) {
		//현재시간구하기
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strNowDate = simpleDateFormat.format(nowDate);

		map.put("r_date", strNowDate);

		if(itemDao.insert(map) == 1)
			return map.get("id").toString();
		return null;
	}

	@Override
	public List<Map<String, Object>> selectAll() {		
		return itemDao.selectAll();
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {		
		return itemDao.detail(map);
	}

	@Override
	public boolean update(Map<String, Object> map) {
		//현재시간구하기
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strNowDate = simpleDateFormat.format(nowDate);

		map.put("m_date", strNowDate);
		return itemDao.update(map);
	}

	@Override
	public void delete(Map<String, Object> map) {
		itemDao.delete(map);
		
	}

	@Override
	public List<Map<String, Object>> selectItemList(Map<String, Object> map) {
		return itemDao.selectItemList(map);
	}

	@Override
	public int getcount(Map<String, Object> map) {
		return itemDao.getcount(map);
	}

	@Override
	public void hitUp(Map<String, Object> detailMap) {
		//조회수증가
		itemDao.hitUp(detailMap);
	}

}
