package com.xin.control;

import com.xin.model.Kind;
import com.xin.model.User;
import com.xin.service.IPetService;
import com.xin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2016/9/4 0004.
 */
@Controller
@RequestMapping("/user")
public class UserController {

   @Autowired
    private IUserService userService;
    @Autowired
    private IPetService petSerivce;

    @RequestMapping("/lmain")
    public String loginMain() {

        return "shop/login.ftl";
    }
    @RequestMapping("/login")
    public String login(User luser, Map map) {
        User ruser = userService.login(luser);
        if(ruser != null) {
            map.put("username", ruser.getUsername());   //传给main.ftl页面使用
            return "shop/main.ftl";
        } else {
            map.put("error", "登录失败，请重新登录！");
            return"shop/login.ftl";
        }
    }

    @RequestMapping("/rmain")
    public String registerMain(Map map) {
      /*
        为了用SpringMVC做校验：必须首先实例化个空对象，并且装在map中，否则出现找不到属性错误
            键的名字是类名首字母小写，遵循契约式编程,"user",值是类的对象；
       */
        User user = new User();
        map.put("user", user);

        List<Kind> kind_list =  petSerivce.queryKindList();
        map.put("kind_list", kind_list);   //将宠物种类列表传到前端页面中
        return "shop/register.ftl";
    }


    /*在用户提交表单后，通过前端的比对，在该方法中判断校验结果;*/
    @RequestMapping("/register")
    public String register(
            @Valid User user, //要有@Valid，必须成对出现
            BindingResult result,
            Map map
    ) {
        if(result.hasErrors()) {
            //为了让验证失败后依然能出现喜好选项框；
            List<Kind> kind_list =  petSerivce.queryKindList();
            map.put("kind_list", kind_list);
            return "shop/register.ftl";
        }

        //为User中Hobby对象赋值： 其中的kindid字段在前端已经自动赋值完了；
        user.getHobby().setUsername(user.getUsername());

        if(userService.register(user)) {       //注册成功
            return"shop/login.ftl";
        }
        return null;
    }


}
