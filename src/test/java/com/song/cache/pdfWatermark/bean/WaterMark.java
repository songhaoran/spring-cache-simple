package com.song.cache.pdfWatermark.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wen-8 on 2017/10/30.
 */
@Data
public class WaterMark implements Serializable {
    /**
     * 水印所在页码
     * 注:如果为null,则在每页都添加
     */
    private Integer pageNum;
    /**
     * 水印横坐标
     * 注:为空时,取中间位置
     */
    private Float x;
    /**
     * 水印纵坐标
     * 注:为空时,取中间位置
     */
    private Float y;
    /**
     * 水印透明度
     */
    private Float transparency;
    /**
     * 水印倾斜角
     */
    private Float radAngle;
    /**
     * 水印状态 0:停用,1:启用
     */
    private Integer status;

    public WaterMark() {
    }

    public WaterMark(Integer pageNum, Float x, Float y, Float transparency, Float radAngle, Integer status) {
        this.pageNum = pageNum;
        this.x = x;
        this.y = y;
        this.transparency = transparency;
        this.radAngle = radAngle;
        this.status = status;
    }
}
