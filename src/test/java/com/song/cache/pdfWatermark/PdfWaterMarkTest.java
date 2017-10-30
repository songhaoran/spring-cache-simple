package com.song.cache.pdfWatermark;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.song.cache.pdfWatermark.bean.PdfWatermark;
import com.song.cache.pdfWatermark.bean.WordWatermark;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen-8 on 2017/10/30.
 */
public class PdfWaterMarkTest {
    public static final String FONT = "STSongStd-Light";
    public static final String DEST = "E:\\new.pdf";
    public static final String SRC = "E:\\阿里巴巴Java开发手册 - 副本.pdf";

    @Test
    public void testWordWaterMark() throws Exception{
        WordWatermark word_01 = new WordWatermark(FONT, "水印文字1-1", 1, 200f, 200f, 0.3f, 0f, 1);
        WordWatermark word_02 = new WordWatermark(FONT, "水印文字1-2", 1, 200f, 300f, 0.6f, 30f, 1);
        WordWatermark word_03 = new WordWatermark(FONT, "水印文字1-3", 1, 200f, 400f, 1.0f, 60f, 1);
        WordWatermark word_04 = new WordWatermark(FONT, "水印文字3-1", 3, 200f, 200f, 0.3f, 0f, 1);
        WordWatermark word_05 = new WordWatermark(FONT, "水印文字3-2", 3, 200f, 300f, 0.6f, 30f, 1);
        WordWatermark word_06 = new WordWatermark(FONT, "水印文字3-3", 3, 200f, 400f, 1.0f, 60f, 1);
        WordWatermark word_07 = new WordWatermark(FONT, "水印文字5-1", 5, 200f, 200f, 0.3f, 0f, 1);
        WordWatermark word_08 = new WordWatermark(FONT, "水印文字5-2", 5, 200f, 300f, 0.6f, 30f, 1);
        WordWatermark word_09 = new WordWatermark(FONT, "水印文字5-3", 5, 200f, 400f, 1.0f, 60f, 1);
        WordWatermark word_10 = new WordWatermark(FONT, "水印文字5-4", 5, 200f, 200f, 1.0f, 30f, 1);
        WordWatermark word_11 = new WordWatermark(FONT, "水印文字33-1", 33, 200f, 300f, 0.5f, 0f, 1);

        List<WordWatermark> words = new ArrayList<>();
        words.add(word_01);
        words.add(word_02);
        words.add(word_03);
        words.add(word_04);
        words.add(word_05);
        words.add(word_06);
        words.add(word_07);
        words.add(word_08);
        words.add(word_09);
        words.add(word_10);
        words.add(word_11);

        PdfWatermark pdfWatermark = new PdfWatermark(words);
        setWaterMark(pdfWatermark);

    }

    public boolean setWaterMark(PdfWatermark pdfWatermark) throws Exception {
        if (pdfWatermark == null || pdfWatermark.getWordsMarks() == null || pdfWatermark.getWordsMarks().size() < 1)
            return false;
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        Document document = new Document(pdfDocument);
        int totalPageNum = pdfDocument.getNumberOfPages();
        String font;
        float x, y, transparency, radAngle;
        Rectangle pageSize;
        PdfFont waterMarkFont;

        PdfCanvas over;
        Paragraph p;

        for (int i = 1; i <= totalPageNum; i++) {
            for (WordWatermark wordMark : pdfWatermark.getWordsMarks()) {
                if (!wordMark.ifEnabled())//判断水印是否可用(水印文字不为空,状态为启用状态)
                    continue;
                if (wordMark.getPageNum() != null && wordMark.getPageNum().intValue() != i)//判断水印是否需要添加到此页面(weinull时每页都添加)
                {
                    continue;
                }
                transparency = wordMark.getTransparency() == null ? 0 : wordMark.getTransparency();
                PdfExtGState gState = new PdfExtGState();
                gState.setFillOpacity(transparency);
                PdfPage page = pdfDocument.getPage(i);
                pageSize = page.getPageSizeWithRotation();
                float right = pageSize.getRight();
                float left = pageSize.getLeft();

                //0.属性
                font = StringUtils.isNotBlank(wordMark.getWordFont()) ? wordMark.getWordFont() : FONT;
                x = wordMark.getX() == null ? (pageSize.getRight() + pageSize.getLeft()) / 2 : wordMark.getX();
                y = wordMark.getY() == null ? (pageSize.getTop() + pageSize.getBottom()) / 2 : wordMark.getY();
                radAngle = wordMark.getRadAngle() == null ? 0 : wordMark.getRadAngle();

                //1.字体
                waterMarkFont = PdfFontFactory.createFont(font, "UniGB-UCS2-H", false);

                //2.透明度


                //3.
                over = new PdfCanvas(pdfDocument.getPage(i));
                over.saveState();
                over.setExtGState(gState);
                p = new Paragraph(wordMark.getWordText()).setFont(waterMarkFont).setFontSize(30);
                document.showTextAligned(p, x, y, i, TextAlignment.CENTER, VerticalAlignment.TOP, radAngle);
                over.restoreState();
            }
        }
        document.close();
        return true;
    }
}
