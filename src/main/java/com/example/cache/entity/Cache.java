package com.example.cache.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Cache<T> {

    private String key;
    private T value;

}
