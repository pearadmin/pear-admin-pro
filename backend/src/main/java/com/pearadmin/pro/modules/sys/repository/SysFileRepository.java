package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysFile;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysFileRepository extends BaseMapper<SysFile> {

    /**
     * 获取文件列表
     *
     * @param request 查询参数
     *
     * @return {@link SysFile}
     * */
    List<SysFile> selectList(SysFileRequest request);
}
