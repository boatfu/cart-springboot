package com.boat.controller;

import com.boat.entity.User;
import com.boat.mapper.UserMapper;
import com.boat.tool.EmailTool;
import com.boat.tool.UserTool;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    UserTool userTool = new UserTool();

    @RequestMapping("/register")
    public String register (){
        return "register";
    }

    @RequestMapping(value = "/emailCheck",method = RequestMethod.POST)
    public  String emailCheck( User user){
        String code = userTool.getCode();
        HttpSession session = null;
        session.setAttribute("user",user);
        session.setAttribute("code",code);

        //生成邮件
        try {
            new Thread(new EmailTool(user.getEmail(), code)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "email";
    }
    @RequestMapping("/registerCheck")
    @ResponseBody
    public String registerCheck(@RequestBody String para){
        HttpSession session = null;

        JSONObject jsonObject = new JSONObject(para);
        if((jsonObject.get("email")).equals(session.getAttribute("code"))){
            userMapper.addUser((User) session.getAttribute("user"));
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("msg","success");

            return jsonObject1.toString();
        }else {
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("msg","failed");
            return jsonObject2.toString();
        }
    }

    @RequestMapping("loginCheck")
    @ResponseBody
    public String loginCheck(@RequestBody String para){
        JSONObject jsonObject = new JSONObject(para);
        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");
        User user = userMapper.checkUser(email);
        if(user.getPassword().equals(password)){
            HttpSession session = null;
            session.setAttribute("user",user);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject.put("msg","success");
            return jsonObject1.toString();
        }else{
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("msg","failed");
            return jsonObject2.toString();
        }
    }

    @RequestMapping(value = "/checkEmail",method = RequestMethod.POST)
    public @ResponseBody String checkUsername(@RequestBody String para){
        JSONObject jsonObject = new JSONObject(para);
        String email = (String)jsonObject.get("email");
        if(userTool.checkEmailForm(email) < 0){
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("msg","邮箱格式不正确");
            return jsonObject3.toString();
        }else {
            if(userMapper.checkUser(email) !=null) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("msg","该邮箱已经存在");
                return jsonObject1.toString();
            } else {
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("msg","ok");
                return jsonObject2.toString();
            }
        }

    }

}
