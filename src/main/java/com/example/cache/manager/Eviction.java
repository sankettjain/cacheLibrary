package com.example.cache.manager;

import com.example.cache.entity.Cache;

public interface Eviction {

    Object get(String key);

    Boolean put(String key, Cache value);

    void printCache();
}
