package com.song.cache.pdfWatermark.bean;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by song on 2017/10/30.
 */
@Data
public class ImgWatermark extends WaterMark {
    /**
     * 图片水印地址
     */
    private String imgUrl;

    public ImgWatermark() {}

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
}
