package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysPowerRepository extends BaseMapper<SysPower> {

    /**
     * 获取权限列表
     *
     * @return {@link SysPower}
     * */
    List<SysPower> selectList();

    /**
     * 根据 userId 获取权限列表
     *
     * @param userId 用户编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectPower(@Param("userId") String userId);

    /**
     * 根据 userId 获取菜单
     *
     * @param userId 用户编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectMenu(String userId);
}
