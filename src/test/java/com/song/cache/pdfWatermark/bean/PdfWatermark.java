package com.song.cache.pdfWatermark.bean;


import lombok.Data;

import java.util.List;

/**
 * Created by song on 2017/10/30.
 */
@Data
public class PdfWatermark {

    /**
     * 文字水印列表
     */
    private List<WordWatermark> wordsMarks;
    /**
     * 图片水印
     */
    private List<ImgWatermark> imgMarks;

    public PdfWatermark() {}

    public PdfWatermark(List<WordWatermark> wordsMarks, List<ImgWatermark> imgMarks) {
        this.wordsMarks = wordsMarks;
        this.imgMarks = imgMarks;
    }
}
