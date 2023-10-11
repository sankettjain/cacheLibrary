package com.example.cache.manager;

import com.example.cache.entity.Cache;

public interface Strategy {

    Boolean addData(String key, Cache value);
    Cache getData(String key);

}
