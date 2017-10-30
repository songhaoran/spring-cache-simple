package com.song.cache.pdfWatermark.bean;

import java.io.Serializable;

/**
 * Created by wen-8 on 2017/10/30.
 */
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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getTransparency() {
        return transparency;
    }

    public void setTransparency(Float transparency) {
        this.transparency = transparency;
    }

    public Float getRadAngle() {
        return radAngle;
    }

    public void setRadAngle(Float radAngle) {
        this.radAngle = radAngle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
