package com.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.journalApp.api.response.WeatherResponse;
import com.journalApp.cache.AppCache;
import com.journalApp.constants.Placeholders;

@Service
public class WeatherService {
	@Value("${weather.api.key}")
	private String apiKey;
	
	
    
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppCache appCache;
	
	@Autowired
	private RedisService redisService;
	
	public WeatherResponse getWeather(String city) {
		WeatherResponse weatherResponse = redisService.get("weather_of_"+ city,WeatherResponse.class);
		if(weatherResponse!=null) {
			return weatherResponse;
		}
		else {
		String finalAPI= appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY, apiKey);
		ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalAPI,HttpMethod.GET,null,WeatherResponse.class);
		WeatherResponse body = response.getBody();
		if(body!=null) {
			redisService.set("weather_of_"+ city, body, 300l);
		}
		return body;
		}
		
	}

}
