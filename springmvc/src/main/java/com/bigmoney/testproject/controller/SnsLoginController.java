package com.bigmoney.testproject.controller;

import com.bigmoney.testproject.service.ItemService;
import com.bigmoney.testproject.service.SnsLoginService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SnsLoginController {

    @Autowired
    SnsLoginService snsLoginService;

    @RequestMapping(value = "/kakaoLogin")
    public ModelAndView modifyItemView(@RequestParam String code, HttpServletRequest request)throws Exception{
        //인가코드
        System.out.println("kakaoLogin code = " + code);

        //인가코드를 이용해서 토근값 가져오기
        String host = "https://kauth.kakao.com/oauth/token";
        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String token = "";
        try {
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true); // 데이터 기록 알려주기

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=596030f6cbaa088976c54efe745729dc");
            sb.append("&redirect_uri=http://localhost:8080/kakaoLogin");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            //responseCode 확인
            int responseCode = urlConnection.getResponseCode();
            System.out.println("kakaoLogin responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("kakaoLogin result = " + result);

            // json parsing
            JSONParser parser = new JSONParser();
            JSONObject elem = (JSONObject) parser.parse(result);

            String access_token = elem.get("access_token").toString();
            String refresh_token = elem.get("refresh_token").toString();
            System.out.println("refresh_token = " + refresh_token);
            System.out.println("access_token = " + access_token);

            token = access_token;

            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("kakao token = " + token);

        //토큰을 이용해서 사용자정보가져오기
        Map<String,Object> kakaoUserInfoMap = new HashMap<>();
        kakaoUserInfoMap = snsLoginService.getKakaoUserInfo(token);
        HttpSession session =  request.getSession();
        session.setAttribute("memberMap", kakaoUserInfoMap);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/selectItemList");
        return mv;
    }
}
