package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Precautionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zhyj on 2017/9/24.
 */
public interface PrecautionaryDao {

    /**
     * 查询预警信息
     * @param conditions status 1：预警信息 2：过期信息 null：全部信息 distributorId 分销商id 不传查全部的
     * @return
     */
    public List<Precautionary> query(Map<String, String> conditions);
}
