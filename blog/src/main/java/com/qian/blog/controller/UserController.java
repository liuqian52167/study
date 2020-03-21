package com.qian.blog.controller;

import com.qian.blog.config.ShiroRealm;
import com.qian.blog.model.User;
import com.qian.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lq
 * @date:2020/03/21
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 创建固定的用户
     * @param model
     * @return
     */
    //RequiresPermissions是shiro提供的一个注解类。主要是用作权限校验的一种方式。
    @RequiresPermissions("user:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    //处理HttpEntity传递过来的数据，一般用来处理非Content-Type: application/x-www-form-urlencoded编码格式的数据。
    //•GET请求中，因为没有HttpEntity，所以@RequestBody并不适用。
    //•POST请求中，通过HttpEntity传递的参数，必须要在请求头中声明数据的类型Content-Type，SpringMVC通过使用HandlerAdapter 配置的HttpMessageConverters来解析HttpEntity中的数据，然后绑定到相应的bean上。

    //总结
    //•在GET请求中，不能使用@RequestBody。
    //•在POST请求，可以使用@RequestBody和@RequestParam，但是如果使用@RequestBody，对于参数转化的配置必须统一。
    public String login(Model model) {

        User user = new User();
        user.setName("刘谦");
        user.setUsername("liuqian");

        userService.insert(user);

        return "创建用户成功";
    }
    /**
     * 删除固定写死的用户
     * @param model
     * @return
     */
    @RequiresPermissions("user:del")
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public String del(Model model) {

        userService.del("liuqian");

        return "删除用户名为liuqian用户成功";

    }

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public String view(Model model) {

        return "这是用户列表页";

    }
    /**
     * 给admin用户添加 userInfo:del 权限
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPermission",method = RequestMethod.GET)
    @ResponseBody
    public String addPermission(Model model) {

        //在sys_role_permission 表中  将 删除的权限 关联到admin用户所在的角色
        //roleMapper.addPermission(1,3);

        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();
        return "给admin用户添加 userInfo:del 权限成功";

    }


}
