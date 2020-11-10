package com.example.test.eache;

import com.example.test.entity.TemplateInfoEntity;

public interface UserEacheService {

    //存值
    public TemplateInfoEntity putUserCache(Long id, TemplateInfoEntity infoEntity);

}
