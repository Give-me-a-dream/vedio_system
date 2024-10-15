package com.example.vediosystem.domain;

import lombok.Data;

import java.util.List;

@Data
public class VideoSelect {
    private List<Integer> sorts;
    private List<Integer> regions;
    private int limit;
    private int page;
}
