package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrderService ordersService;

    //未分页代码
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll()throws Exception{
//
//        List<Orders>list= ordersService.findAll();
//
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("ordersList",list);
//        mv.setViewName("orders-list");
//        return  mv;
//    }
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{

        ModelAndView mv=new ModelAndView();

        List<Orders>list=ordersService.findAll(page,size);

        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String  ordersId)throws Exception{
        Orders orders= ordersService.findById(ordersId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }


}
