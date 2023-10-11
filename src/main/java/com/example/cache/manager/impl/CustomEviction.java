package com.example.cache.manager.impl;

import com.example.cache.entity.Cache;
import com.example.cache.manager.Eviction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomEviction implements Eviction {

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public Boolean put(String key, Cache value) {
        return null;
    }

    @Override
    public void printCache() {
        System.out.println("You are in Custom Eviction");
    }
}
