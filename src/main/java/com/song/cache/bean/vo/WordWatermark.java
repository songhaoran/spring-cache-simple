package com.song.cache.bean.vo;

/**
 * Created by wen-8 on 2017/10/30.
 */
public class WordWatermark {
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
}
