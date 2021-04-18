package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import org.apache.ibatis.annotations.Mapper;

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
    List<SysPower> selectListByUserId(String userId);

    /**
     * 获取权限树
     *
     * @return {@link SysPower}
     * */
    List<SysPower> selectTree();

    /**
     * 根据 userId 获取权限树
     *
     * @param userId 用户编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectTreeByUserId(String userId);

    /**
     * 获取权限子集
     *
     * @param parent 父级编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectChildren(String parent);

    /**
     * 根据 userId 获取权限子集
     *
     * @param userId 用户编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectChildrenByUserId(String parent,String userId);

}
