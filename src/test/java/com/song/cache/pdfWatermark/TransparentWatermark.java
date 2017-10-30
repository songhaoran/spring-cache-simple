package com.song.cache.pdfWatermark;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;


/**
 * Created by song on 2017/10/30.
 */
public class TransparentWatermark {
    public static final String DEST = "E:\\new.pdf";
    public static final String SRC = "E:\\阿里巴巴Java开发手册 - 副本.pdf";
    public static final String IMG = "E:\\微信图片_20171015153307.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
//        new TransparentWatermark().manipulatePdf(DEST);
        new TransparentWatermark().markEveryPage();
    }


    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
       /* PdfCanvas under = new PdfCanvas(pdfDoc.getFirstPage().newContentStreamBefore(), new PdfResources(), pdfDoc);
//        under.setFillColor(Color.BLACK);
        PdfFont font = PdfFontFactory.createFont(FontProgramFactory.createFont(FontConstants.HELVETICA));
        Paragraph p = new Paragraph("加在下面的水印Under")
                .setFont(font).setFontSize(15);
        new Canvas(under, pdfDoc, pdfDoc.getDefaultPageSize())
                .showTextAligned(p, 297, 550, 1, TextAlignment.CENTER, VerticalAlignment.TOP, 0);*/

        PdfFont font = PdfFontFactory.createFont(FontProgramFactory.createFont(FontConstants.HELVETICA));
        PdfCanvas over = new PdfCanvas(pdfDoc.getFirstPage());
        over.setFillColor(Color.BLACK);
        Paragraph p = new Paragraph("加在上面的水印Top").setFont(font).setFontSize(30);
        /*new Canvas(over, pdfDoc, pdfDoc.getDefaultPageSize())
                .showTextAligned(p, 297, 500, 1, TextAlignment.CENTER, VerticalAlignment.TOP, 0);*/
        /*p = new Paragraph("This TRANSPARENT watermark is added ON TOP OF the existing content")
                .setFont(font).setFontSize(15);*/
        over.saveState();
        PdfExtGState gs1 = new PdfExtGState();
        gs1.setFillOpacity(0.2f);//透明度
        over.setExtGState(gs1);
        new Canvas(over, pdfDoc, pdfDoc.getDefaultPageSize())
                .showTextAligned(p, 297, 450, 1, TextAlignment.CENTER, VerticalAlignment.TOP, 0);
        over.restoreState();
        pdfDoc.close();
    }

    protected void markEveryPage() throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        Document doc = new Document(pdfDoc);
        int n = pdfDoc.getNumberOfPages();
//        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
//        BaseFont baseFont = BaseFont.createFont("STSong-Light", BaseFont.IDENTITY_H, false);
//        PdfFontFactory.createFont(FontCon)
//        Font newFont = new Font(baseFont, 30, Font.NORMAL);
        PdfFont newFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

        Paragraph p = new Paragraph("我是中文水印!").setFont(newFont);
        // image watermark
        ImageData img = ImageDataFactory.create(IMG);
        //  Implement transformation matrix usage in order to scale image
        float w = img.getWidth();
        float h = img.getHeight();
        // transparency
        PdfExtGState gs1 = new PdfExtGState();
        gs1.setFillOpacity(0.3f);
        // properties
        PdfCanvas over;
        Rectangle pagesize;
        float x, y;
        // loop over every page
        for (int i = 1; i <= n; i++) {
            PdfPage pdfPage = pdfDoc.getPage(i);
            pagesize = pdfPage.getPageSizeWithRotation();
            pdfPage.setIgnorePageRotationForContent(true);

            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = new PdfCanvas(pdfDoc.getPage(i));
            over.saveState();
            over.setExtGState(gs1);
            if (i % 2 == 1) {
                doc.showTextAligned(p, x, y, i, TextAlignment.CENTER, VerticalAlignment.TOP, 0);
            } else {
                over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2), false);
            }
            over.restoreState();
        }
        doc.close();
    }
}
