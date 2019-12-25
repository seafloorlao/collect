package com.malt.collect.controller;

import com.malt.collect.entity.User;
import com.malt.collect.service.UserService;
import com.malt.collect.util.SendEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;


@Controller
public class FormCollectController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    @ResponseBody
    public Boolean insertUser(String brand, String name, String phone, String weChat) throws Exception {
        User user = new User();
        user.setBrand(brand);
        user.setName(name);
        user.setPhone(phone);
        user.setWeChat(weChat);
        user.setGmtCreate(LocalDateTime.now());
        boolean save = userService.save(user);
        if (save) {
            SendEmailUtils.sendEmail(user);
        }
        return save;
    }

    @GetMapping("/index")
    public String hello(Model model, Integer type) {
        model.addAttribute("type", type);
        return "index";
    }
}
