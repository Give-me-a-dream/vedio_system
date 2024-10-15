package com.example.vediosystem.domain;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barrage {

    private String time;
    private int type;
    private String color;
    private String author;
    private String text;
    private String vid;

    public Barrage(String time, Integer type, String color, String author, String text, String id) {
        this.time = time;
        this.type = type;
        this.color = color;
        this.author = author;
        this.text = text;
        this.vid = id;
    }

    @Override
    public String toString() {
        return "Barrage{" +
                "time='" + time + '\'' +
                ", type=" + type +
                ", color='" + color + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", vid='" + vid + '\'' +
                '}';
    }
}
