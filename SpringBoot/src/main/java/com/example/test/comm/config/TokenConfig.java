package com.example.test.comm.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "token")
@Component
public class TokenConfig {
	
	private Integer effectiveTime;
	private String secret;
	private String cookieName;
	private String redisHeader;
	private final Exclude exclude = new Exclude();
	
	public Integer getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Integer effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	
	public String getRedisHeader() {
		return redisHeader;
	}

	public void setRedisHeader(String redisHeader) {
		this.redisHeader = redisHeader;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public Exclude getExclude() {
		return exclude;
	}
	
	public static class Exclude {
		private List<String> urls;
		public List<String> getUrls() {
			return urls;
		}
		public void setUrls(List<String> urls) {
			this.urls = urls;
		}
	}
	
}
