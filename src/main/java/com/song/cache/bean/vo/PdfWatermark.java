package com.song.cache.bean.vo;

import java.util.List;

/**
 * Created by wen-8 on 2017/10/30.
 */
public class PdfWatermark {
    /**
     * 对应数据库 finance_water_mark.finance_id
     * 产品id
     */
    private Long financeId;

    private List<WordWatermark> wordsMarks;

    private List<ImgWatermark> imgMarks;

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

    public List<WordWatermark> getWordsMarks() {
        return wordsMarks;
    }

    public void setWordsMarks(List<WordWatermark> wordsMarks) {
        this.wordsMarks = wordsMarks;
    }

    public List<ImgWatermark> getImgMarks() {
        return imgMarks;
    }

    public void setImgMarks(List<ImgWatermark> imgMarks) {
        this.imgMarks = imgMarks;
    }
}
