package com.song.cache.service;

import com.song.cache.bean.dto.FinanceWaterMark;
import com.song.cache.bean.vo.PdfWatermark;
import com.song.cache.dao.FinanceWaterMarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by wen-8 on 2017/10/30.
 */
@Service
public class PdfWatermarkService {
    @Autowired
    private FinanceWaterMarkMapper financeWaterMarkMapper;

    PdfWatermark getPdfWatermarkByFinanceId(Integer financeId) {
        PdfWatermark pdfWatermark = financeWaterMarkMapper.selectPdfWatermarkByFinanceId(financeId);
        return pdfWatermark;
    }

    List<FinanceWaterMark> listFinanceWaterMarkByFinanceId(Integer financeId) {
        List<FinanceWaterMark> list = financeWaterMarkMapper.selectFinanceWaterMarkByFinanceId(financeId);
        return list;
    }
}
