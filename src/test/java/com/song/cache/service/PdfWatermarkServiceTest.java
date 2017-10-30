package com.song.cache.service;

import com.song.cache.bean.dto.FinanceWaterMark;
import com.song.cache.bean.vo.PdfWatermark;
import com.song.cache.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by Song on 2017/7/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class PdfWatermarkServiceTest {
    @Autowired
    private PdfWatermarkService pdfWatermarkService;

    @Test
    public void testGetPdfWatermarkByFinanecId() {
        PdfWatermark pdfWatermarkByFinanceId = pdfWatermarkService.getPdfWatermarkByFinanceId(121);
        System.out.println();
    }

    @Test
    public void testListFinanceWaterMarkByFinanceId() {
        List<FinanceWaterMark> list = pdfWatermarkService.listFinanceWaterMarkByFinanceId(121);
        System.out.println();
    }
}
