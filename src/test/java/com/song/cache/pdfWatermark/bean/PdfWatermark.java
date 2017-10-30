package com.song.cache.pdfWatermark.bean;


import java.util.List;

/**
 * Created by song on 2017/10/30.
 */

public class PdfWatermark {

    /**
     * 文字水印列表
     */
    private List<WordWatermark> wordsMarks;
    /**
     * 图片水印
     */
    private List<ImgWatermark> imgMarks;

    public PdfWatermark() {
    }

    public PdfWatermark(List<WordWatermark> wordsMarks) {
        this.wordsMarks = wordsMarks;
    }

    public PdfWatermark(List<WordWatermark> wordsMarks, List<ImgWatermark> imgMarks) {
        this.wordsMarks = wordsMarks;
        this.imgMarks = imgMarks;
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
