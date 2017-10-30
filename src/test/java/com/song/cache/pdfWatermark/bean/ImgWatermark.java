package com.song.cache.pdfWatermark.bean;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by song on 2017/10/30.
 */

public class ImgWatermark extends WaterMark {
    /**
     * 图片水印地址
     */
    private String imgUrl;

    public ImgWatermark(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ImgWatermark() {
    }

    public ImgWatermark(Integer pageNum, Float x, Float y, Float transparency, Float radAngle, Integer status, String imgUrl) {
        super(pageNum, x, y, transparency, radAngle, status);
        this.imgUrl = imgUrl;
    }

    public boolean ifEnabled() {
        if (StringUtils.isNotBlank(imgUrl) && this.getStatus() == 1) {
            return true;
        }
        return false;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
