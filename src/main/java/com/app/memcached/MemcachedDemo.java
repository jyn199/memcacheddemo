package com.app.memcached;

import org.springframework.stereotype.Component;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
public class MemcachedDemo {
	
	@ReadThroughSingleCache(expiration=300, namespace="demo")
	public String getString(@ParameterValueKeyProvider String key){
		return "demo";
	}
}
