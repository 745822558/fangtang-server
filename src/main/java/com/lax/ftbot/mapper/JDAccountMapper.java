package com.lax.ftbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lax.ftbot.pojo.JDAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName JDAccountMapper
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 15:27
 **/
@Mapper
public interface JDAccountMapper extends BaseMapper<JDAccount> {
    /**
     * 查询当前ptpin是否存在
     * @param ptpin
     * @return
     */
    Integer ptpinIsExist(String ptpin);

}
