package com.zm.anno.aop.controller;

import com.zm.anno.aop.entity.SysUser;
import com.zm.anno.aop.service.SysUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2021-04-11 20:13:45
 */
@RestController
@RequestMapping("sysUser")
@Data
@Slf4j
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUser selectOne(String id) {
        log.info("2222");
        return this.sysUserService.queryById(id);
    }

}