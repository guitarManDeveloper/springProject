package com.bigmoney.testproject.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SnsLoginServiceImpl implements SnsLoginService {

	@Override
	public Map<String, Object> getKakaoUserInfo(String access_token) throws Exception {
		String host = "https://kapi.kakao.com/v2/user/me";
		Map<String, Object> result = new HashMap<>();
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

			result.put("id", id);
			result.put("nickname", nickname);
			result.put("age_range", age_range);

			br.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
