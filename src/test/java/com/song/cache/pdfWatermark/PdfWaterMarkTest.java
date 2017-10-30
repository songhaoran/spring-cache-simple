package com.song.cache.pdfWatermark;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
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
import com.song.cache.pdfWatermark.bean.ImgWatermark;
import com.song.cache.pdfWatermark.bean.PdfWatermark;
import com.song.cache.pdfWatermark.bean.WordWatermark;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.PaddingScheme;
import org.junit.Test;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2017/10/30.
 */
public class PdfWaterMarkTest {
    public static final String FONT = "STSongStd-Light";
    public static final String DEST = "E:\\new.pdf";
    public static final String SRC = "https://doc.meixinglobal.com/docs/20170502/9ae1ea67-3e48-43ad-ba31-3dae7c5affe1.pdf";
//    public static final String SRC = "E:\\阿里巴巴Java开发手册 - 副本.pdf";
    public static final String IMG_URL = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=634098145,264198475&fm=27&gp=0.jpg";
    @Test
    public void testWordWaterMark() throws Exception {
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

        ImgWatermark img_01 = new ImgWatermark(2, 100f, 200f, 0.2f, 0.2f, 1, IMG_URL);
        ImgWatermark img_02 = new ImgWatermark(2, 100f, 500f, 0.2f, 0.2f, 1, IMG_URL);
        ImgWatermark img_03 = new ImgWatermark(4, 100f, 200f, 0.2f, 0.2f, 1, IMG_URL);
        ImgWatermark img_04 = new ImgWatermark(6, 100f, 200f, 0.2f, 0.2f, 1, IMG_URL);

        List<ImgWatermark> imgs = new ArrayList<>();
        imgs.add(img_01);
        imgs.add(img_02);
        imgs.add(img_03);
        imgs.add(img_04);

        PdfWatermark pdfWatermark = new PdfWatermark(words,imgs);
        setWaterMark(pdfWatermark);

    }

    public boolean setWaterMark(PdfWatermark pdfWatermark) throws Exception {
        if (pdfWatermark == null
                || ((pdfWatermark.getWordsMarks() == null || pdfWatermark.getWordsMarks().size() < 1) && (pdfWatermark.getImgMarks() == null || pdfWatermark.getImgMarks().size() < 1)))
            return false;
        URL url = new URL(SRC);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();

        PdfDocument pdfDocument = new PdfDocument(new PdfReader(inputStream), new PdfWriter(DEST));
        Document document = new Document(pdfDocument);
        int totalPageNum = pdfDocument.getNumberOfPages();
        String font;
        float x, y, transparency, radAngle;
        Rectangle pageSize;
        PdfFont waterMarkFont;
        PdfCanvas over;
        Paragraph p;

        for (int i = 1; i <= totalPageNum; i++) {
            //文字水印
            if (pdfWatermark.getWordsMarks() != null && pdfWatermark.getWordsMarks().size() > 0) {
                for (WordWatermark wordMark : pdfWatermark.getWordsMarks()) {
                    if (wordMark == null)
                        continue;
                    if (!wordMark.ifEnabled())//判断水印是否可用(水印文字不为空,状态为启用状态)
                        continue;
                    if (wordMark.getPageNum() != null && wordMark.getPageNum().intValue() != i)//判断水印是否需要添加到此页面(weinull时每页都添加)
                        continue;

                    PdfPage page = pdfDocument.getPage(i);
                    pageSize = page.getPageSizeWithRotation();
//                float right = pageSize.getRight();
//                float left = pageSize.getLeft();
//                float top = pageSize.getTop();
//                float bottom = pageSize.getBottom();
//                float width = pageSize.getWidth();
//                float height = pageSize.getHeight();

                    //0.
                    font = StringUtils.isNotBlank(wordMark.getWordFont()) ? wordMark.getWordFont() : FONT;
                    x = wordMark.getX() == null ? (pageSize.getRight() + pageSize.getLeft()) / 2 : wordMark.getX();
                    y = wordMark.getY() == null ? (pageSize.getTop() + pageSize.getBottom()) / 2 : wordMark.getY();
                    radAngle = wordMark.getRadAngle() == null ? 0 : wordMark.getRadAngle();
                    transparency = wordMark.getTransparency() == null ? 0 : wordMark.getTransparency();

                    //1.透明度
                    PdfExtGState gState = new PdfExtGState();
                    gState.setFillOpacity(transparency);

                    //2.字体
                    waterMarkFont = PdfFontFactory.createFont(font, "UniGB-UCS2-H", false);

                    //3.加水印
                    over = new PdfCanvas(pdfDocument.getPage(i));
                    over.saveState();
                    over.setExtGState(gState);
                    p = new Paragraph(wordMark.getWordText()).setFont(waterMarkFont).setFontSize(30);
                    document.showTextAligned(p, x, y, i, TextAlignment.CENTER, VerticalAlignment.TOP, radAngle);
                    over.restoreState();
                }
            }

            //图片水印
            if (pdfWatermark.getImgMarks() != null && pdfWatermark.getImgMarks().size() > 0) {
                for (ImgWatermark imgMark : pdfWatermark.getImgMarks()) {
                    if (imgMark == null)
                        continue;
                    if (!imgMark.ifEnabled())//判断水印是否可用(水印图片链接不为空,状态为启用状态)
                        continue;
                    if (imgMark.getPageNum() != null && imgMark.getPageNum().intValue() != i)//判断水印是否需要添加到此页面(为null时每页都添加)
                        continue;

                    //0.
                    ImageData imageData = ImageDataFactory.create(IMG_URL);
                    if (imageData == null)
                        continue;
                    PdfPage page = pdfDocument.getPage(i);
                    pageSize = page.getPageSizeWithRotation();
                    x = imgMark.getX() == null ? (pageSize.getRight() + pageSize.getLeft() - imageData.getWidth()) / 2 : imgMark.getX();
                    y = imgMark.getY() == null ? (pageSize.getTop() + pageSize.getBottom() - imageData.getHeight()) / 2 : imgMark.getY();
                    radAngle = imgMark.getRadAngle() == null ? 0 : imgMark.getRadAngle();
                    transparency = imgMark.getTransparency() == null ? 0 : imgMark.getTransparency();

                    //1.透明度
                    PdfExtGState gState = new PdfExtGState();
                    gState.setFillOpacity(transparency);

                    //2.加水印
                    over = new PdfCanvas(pdfDocument.getPage(i));
                    over.setExtGState(gState);
                    over.saveState();

                    /**
                     * a:width
                     * d:height
                     * e:x
                     * f:y
                     */
                    over.addImage(imageData, imageData.getWidth(), 0, 0, imageData.getHeight(), x, y, false);
                    over.restoreState();
                }
            }
        }
        document.close();
        return true;
    }
}
