package com.example.cache.manager.impl;

import com.example.cache.entity.Cache;
import com.example.cache.manager.Strategy;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Data
public class QueueStrategy implements Strategy {

    private Map<String, Cache> keyToval;
    private Queue<String> queue;
    private Integer capacity;

    public QueueStrategy(Integer capacity) {
        this.capacity = capacity;
        this.keyToval = new HashMap<>();
        this.queue = new ArrayDeque<>();
    }

    @Override
    public Boolean addData(String key, Cache value) {
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
    public Cache getData(String key) {
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
}
