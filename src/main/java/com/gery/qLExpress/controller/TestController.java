package com.gery.qLExpress.controller;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: gery-demo-2023
 * @ClassName TestController
 * @description:
 * @author: yaowenhua
 * @create: 2023-09-12 17:31
 * @Version 1.0
 **/
public class TestController {
    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = " b   > a ";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }
}
