package com.song.cache.pdfWatermark.bean;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by song on 2017/10/30.
 */

public class WordWatermark extends WaterMark {
    /**
     * 文字水印-字体
     */
    private String wordFont;

    /**
     * 文字水印-内容
     */
    private String wordText;


    public WordWatermark() {
    }

    public WordWatermark(String wordFont, String wordText, Integer pageNum, Float x, Float y, Float transparency, Float radAngle, Integer status) {
        super(pageNum, x, y, transparency, radAngle, status);
        this.wordFont = wordFont;
        this.wordText = wordText;
    }

    public boolean ifEnabled() {
        if (StringUtils.isNotBlank(wordText) && this.getStatus() == 1) {
            return true;
        }
        return false;
    }

    public String getWordFont() {
        return wordFont;
    }

    public void setWordFont(String wordFont) {
        this.wordFont = wordFont;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }
}
