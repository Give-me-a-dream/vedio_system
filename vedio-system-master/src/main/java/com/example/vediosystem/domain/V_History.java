package com.example.vediosystem.domain;

import java.time.LocalDateTime;

public class V_History extends Video{
    private String uploaderName;

    private String prinvinceName;

    private int videoId;

    private LocalDateTime watchTime;

    private double historyTime;

    public double getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(double historyTime) {
        this.historyTime = historyTime;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }


    public LocalDateTime getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(LocalDateTime watchTime) {
        this.watchTime = watchTime;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public String getPrinvinceName() {
        return prinvinceName;
    }

    public void setPrinvinceName(String prinvinceName) {
        this.prinvinceName = prinvinceName;
    }
}
