package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IUserService;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll()throws Exception{

        ModelAndView mv=new ModelAndView();
        List <UserInfo>list=userService.findAll();

        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username='tom'")
    public String save(UserInfo userInfo)throws Exception{

        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name ="id",required = true) String userId)throws Exception{
        UserInfo userInfo = userService.findById(userId);

        List<Role>list=userService.findOtherRoles(userId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[]roleIds)throws Exception{

        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

}
