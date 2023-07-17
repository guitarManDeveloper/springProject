package com.bigmoney.testproject.code.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigmoney.testproject.code.dao.CommonDao;


@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonDao commonDao;

	@Override
	public List<Map<String, Object>> getCode() {		
		return commonDao.getCode();
	}

}
