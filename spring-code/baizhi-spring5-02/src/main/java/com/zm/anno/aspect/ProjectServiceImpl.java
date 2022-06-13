package com.zm.anno.aspect;

/**
 * @author minzhang
 * @date 2022/06/01 17:18
 **/
public class ProjectServiceImpl implements ProjectService{

    @Override
    public void name(String name) {
        System.out.println("产品名称："+ name);

    }
}
