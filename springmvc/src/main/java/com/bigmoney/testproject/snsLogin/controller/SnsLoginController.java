package com.bigmoney.testproject.snsLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bigmoney.testproject.snsLogin.service.SnsLoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SnsLoginController {

    @Autowired
    SnsLoginService snsLoginService;

    
    
    static String KAKAO_CLIENT_ID = "596030f6cbaa088976c54efe745729dc";
    static String KAKAO_REDIRECT_URI = "http://localhost:8080/kakaoLogin";
    
    @RequestMapping(value = "/kakaoLogin")
    public ModelAndView modifyItemView(@RequestParam String code, HttpServletRequest request)throws Exception{
        //인가코드
        System.out.println("kakaoLogin code = " + code);

        //인가코드를 이용해서 토근값 가져오기
        Map<String, Object> kakaoLoginMap = new HashMap<String, Object>();
        kakaoLoginMap.put("apiUrl", "https://kauth.kakao.com/oauth/token");
        kakaoLoginMap.put("requestMethod","POST");
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id="+KAKAO_CLIENT_ID);
        sb.append("&redirect_uri="+KAKAO_REDIRECT_URI);
        sb.append("&code=" + code);
        kakaoLoginMap.put("queryString", sb);

        String token = snsLoginService.getToken(kakaoLoginMap);

        //토큰을 이용해서 사용자정보가져오기
        snsLoginService.getKakaoUserInfo(token,request);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/selectItemList");
        return mv;
    }
}
