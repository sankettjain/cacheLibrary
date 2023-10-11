package com.example.cache.manager.impl;

import com.example.cache.entity.Cache;
import com.example.cache.manager.Eviction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Data
@NoArgsConstructor
public class LRUEviction implements Eviction {

    Map<String, Cache> keyToval;
    Queue<String> queue;
    Integer capacity;

    public LRUEviction(Integer capacity) {
        this.capacity = capacity;
        this.keyToval = new HashMap<>();
        this.queue = new ArrayDeque<>();
    }

    @Override
    public Object get(String key) {

        try {
            if (keyToval.containsKey(key)) {
                queue.remove(key);
                queue.offer(key);
                return keyToval.get(key);
            }

        } catch (Exception e) {
            System.out.println("Exception occured while getting the value from Cache: " + e);
        }
        return null;
    }

    @Override
    public Boolean put(String key, Cache value) {
        try {
            if (keyToval.containsKey(key)) {
                queue.remove(key);
                queue.offer(key);
            } else {
                if (queue.size() < capacity) {
                    queue.offer(key);
                } else {
                    keyToval.remove(queue.poll());
                    queue.offer(key);
                }
                keyToval.put(key, value);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception occured while adding the value in Cache: " + e);
            return false;
        }
    }

    @Override
    public void printCache() {

        System.out.println(keyToval);
    }
}
