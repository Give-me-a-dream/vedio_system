package com.example.vediosystem.domain;

import com.baomidou.mybatisplus.annotation.TableField;

public class U_Video extends Video{
    private String uploaderName;

    private String prinvinceName;

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
