package com.example.cache.main;

import com.example.cache.entity.Cache;
import com.example.cache.enums.EvictionPolicy;
import com.example.cache.manager.impl.CustomEviction;
import com.example.cache.manager.Eviction;
import com.example.cache.manager.impl.LRUEviction;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheLibrary {

    private final Integer DEFAULT_CAPACITY = 2;

    private EvictionPolicy evictionPolicy;
    private Integer capacity;
    private Eviction customEviction;

    public CacheLibrary(EvictionPolicy evictionPolicy, Integer capacity, Eviction customEviction) {
        this.evictionPolicy = evictionPolicy;
        this.capacity = capacity;
        this.customEviction = customEviction;
    }


    public Eviction getEvictionPolicy() {
        if (EvictionPolicy.LRU.equals(evictionPolicy)) {
            return new LRUEviction(capacity);
        } else if (EvictionPolicy.CUSTOM.equals(evictionPolicy)) {
            return customEviction;
        } else {
            return new LRUEviction(DEFAULT_CAPACITY);
        }

    }


    public static void main(String[] args) {


        //LRU Implementation

        CacheLibrary cacheLibrary = new CacheLibrary(EvictionPolicy.LRU, 2, null);

        Eviction eviction = cacheLibrary.getEvictionPolicy();

        Cache<String> cache = new Cache("001", "Sample 1");
        eviction.put(cache.getKey(), cache);

        Cache<String> cache1 = new Cache("003", "Sample 3");
        eviction.put(cache1.getKey(), cache1);

        System.out.println("Value of key: " + cache1.getKey() + " : " + eviction.get(cache1.getKey()));

        Cache<String> cache2 = new Cache("004", "Sample 4");
        eviction.put(cache2.getKey(), cache2);
        eviction.printCache();

        //-- Custom implementation Evcition

        CacheLibrary cacheLibraryCustom = new CacheLibrary(EvictionPolicy.CUSTOM, 2, new CustomEviction());
        Eviction customEviction = cacheLibraryCustom.getEvictionPolicy();
        customEviction.printCache();


    }
}
