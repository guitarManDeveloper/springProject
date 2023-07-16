package com.bigmoney.testproject.service;

import java.util.List;
import java.util.Map;

public interface SnsLoginService {

	Map<String, Object> getKakaoUserInfo(String token)throws Exception;
}
