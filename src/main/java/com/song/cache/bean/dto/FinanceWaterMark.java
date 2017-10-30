package com.song.cache.bean.dto;

public class FinanceWaterMark {
    /**
     * 对应数据库 finance_water_mark.id
     * 
     */
    private Integer id;

    /**
     * 对应数据库 finance_water_mark.finance_id
     * 产品id
     */
    private Long financeId;

    /**
     * 对应数据库 finance_water_mark.type
     * 0:文字水印;1:图片水印
     */
    private Byte type;

    /**
     * 对应数据库 finance_water_mark.word_font
     * 字体大小
     */
    private Double wordFont;

    /**
     * 对应数据库 finance_water_mark.word_text
     * 文字水印内容
     */
    private String wordText;

    /**
     * 对应数据库 finance_water_mark.img_url
     * 图片水印链接
     */
    private String imgUrl;

    /**
     * 对应数据库 finance_water_mark.status
     * 0:停用;1:启用
     */
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getWordFont() {
        return wordFont;
    }

    public void setWordFont(Double wordFont) {
        this.wordFont = wordFont;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}