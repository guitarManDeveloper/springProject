package com.bigmoney.testproject.snsLogin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SnsLoginService {

	void getKakaoUserInfo(String token,HttpServletRequest request)throws Exception;

	String getToken(Map<String, Object> kakaoLoginMap)throws Exception;
}
