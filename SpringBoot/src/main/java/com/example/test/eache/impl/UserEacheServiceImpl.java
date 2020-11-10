package com.example.test.eache.impl;

import com.example.test.eache.UserEacheService;
import com.example.test.entity.TemplateInfoEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "template")
public class UserEacheServiceImpl implements UserEacheService {

    @Override
    @CachePut(key = "'md5_'+#id")
    public TemplateInfoEntity putUserCache(Long id, TemplateInfoEntity infoEntity) {
        return infoEntity;
    }
}
