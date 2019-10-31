package com.dust.copybean.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SourceA {

    private int id;
    private String username;
    private double amount;

    private List<Integer> list;

    private int[] nums;

    private Map<String, String> map;
}
