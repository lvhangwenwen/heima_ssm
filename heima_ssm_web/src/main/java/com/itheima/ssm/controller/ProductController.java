package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @RequestMapping("/save.do")
    public String save(Product product){

        productService.save(product);
        return "redirect:findAll.do";
    }


    //这是查询所有功能
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv=new ModelAndView();
        List<Product> all = productService.findAll();
        mv.addObject("productList",all);

        mv.setViewName("product-list");
        return mv;
    }
}
