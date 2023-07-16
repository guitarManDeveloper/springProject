package com.bigmoney.testproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bigmoney.testproject.service.CommonService;

@Controller
public class CommonCodeController {

	@Autowired
	CommonService commonService;

	/**
	 * 공통코드 데이터조회
	 * @return List<Map<String, Object>>
	 */
	@PostMapping("/comcode")
	@ResponseBody
	public List<Map<String, Object>> commoncode(){
		List<Map<String, Object>> lists = commonService.getCode();
		return lists;
	}
	
}
