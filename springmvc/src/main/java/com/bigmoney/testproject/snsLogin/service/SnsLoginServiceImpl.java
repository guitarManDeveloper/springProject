package com.bigmoney.testproject.snsLogin.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.bigmoney.testproject.snsLogin.vo.SnsLoginVO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SnsLoginServiceImpl implements SnsLoginService {

	@Override
	public void getKakaoUserInfo(String access_token,HttpServletRequest request) throws Exception {
		String host = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(host);

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
			urlConnection.setRequestMethod("GET");

			int responseCode = urlConnection.getResponseCode();
			System.out.println("responseCode = " + responseCode);


			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line = "";
			String res = "";
			while((line=br.readLine())!=null)
			{
				res+=line;
			}

			System.out.println("res = " + res);


			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(res);
			JSONObject kakao_account = (JSONObject) obj.get("kakao_account");
			JSONObject properties = (JSONObject) obj.get("properties");


			String id = obj.get("id").toString();
			String nickname = properties.get("nickname").toString();
			String age_range = kakao_account.get("age_range").toString();

			SnsLoginVO snsLoginVO = new SnsLoginVO();
			snsLoginVO.setUserId(id);
			snsLoginVO.setUserName(nickname);
			snsLoginVO.setUserAge(age_range);
			
			HttpSession session =  request.getSession();
	        session.setAttribute("loginVO", snsLoginVO);
			
			
			br.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public String getToken(Map<String, Object> kakaoLoginMap) throws Exception {
		String host = (String)kakaoLoginMap.get("apiUrl");
        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String token = "";
        try {
            urlConnection.setRequestMethod((String)kakaoLoginMap.get("requestMethod"));
            urlConnection.setDoOutput(true); // 데이터 기록 알려주기

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
   
            StringBuilder sb = new StringBuilder();
            sb = (StringBuilder)kakaoLoginMap.get("queryString");
            bw.write(sb.toString());
            bw.flush();

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
		return token;
	}
}
