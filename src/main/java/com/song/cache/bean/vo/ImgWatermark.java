package com.song.cache.bean.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by wen-8 on 2017/10/30.
 */
public class ImgWatermark {
    /**
     * 对应数据库 finance_water_mark.img_url
     * 图片水印链接
     */
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ImgWatermark() {
    }

    public ImgWatermark(String imgUrl) {
        if (StringUtils.isNotBlank(imgUrl))
            this.imgUrl = imgUrl;
        else
            return;
    }
}
