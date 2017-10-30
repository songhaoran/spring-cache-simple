package com.song.cache.dao;


import com.song.cache.bean.dto.FinanceWaterMark;
import com.song.cache.bean.vo.PdfWatermark;

import java.util.List;

public interface FinanceWaterMarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceWaterMark record);

    int insertSelective(FinanceWaterMark record);

    FinanceWaterMark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceWaterMark record);

    int updateByPrimaryKey(FinanceWaterMark record);

    PdfWatermark selectPdfWatermarkByFinanceId(Integer financeId);

    List<FinanceWaterMark> selectFinanceWaterMarkByFinanceId(Integer financeId);
}