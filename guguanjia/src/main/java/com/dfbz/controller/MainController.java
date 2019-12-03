package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysResourceService;
import com.dfbz.service.SysUserService;
import com.dfbz.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/12/2
 */
/*@RestController
public class MainController {

    @Autowired
    SysUserService service;

    @Autowired
    SysResourceService sysResourceService;

    @RequestMapping("login")
    public Result login(@RequestBody Map<String,Object> params, HttpSession session){
        Result result = new Result();
        if (params.containsKey("code")&&!StringUtils.isEmpty(params.get("code"))){
            if (params.containsKey("username")&&!StringUtils.isEmpty(params.get("username"))&&
                    params.containsKey("password")&&!StringUtils.isEmpty(params.get("password"))){

                SysUser sysUser = new SysUser();
                String username = (String) params.get("username");
                String password = (String) params.get("password");
                sysUser.setUsername(username);
                sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password)+username));
                SysUser loginUser = service.selectOne(sysUser);
                if (loginUser!=null){
                    result.setMsg("登录成功");
                    result.setSuccess(true);
                    HashMap<Object, Object> map = new HashMap<>();
                    map.put("username",username);
                    map.put("password",loginUser.getId());

                    List<SysResource> sysResources = sysResourceService.selectAllByUid(loginUser.getId());

                    map.put("resources",sysResources);
                    result.setObj(map);


                    session.setAttribute("user",sysUser);
                    session.setAttribute("resources",sysResources);

                }


            }



        }


        return result;
    }
}*/

@RestController
public class MainController{


    @Autowired
    SysUserService sysUserService;

    @RequestMapping("login")
    public Result login(@RequestBody Map<String,Object> params,HttpSession session){



        System.out.println(params);


        Result result = new Result();
        //判断验证码
        if (params.containsKey("code")&&!StringUtils.isEmpty(params.get("code"))){
            if (params.containsKey("username")&&!StringUtils.isEmpty(params.get("username"))
                    &&params.containsKey("password")&&!StringUtils.isEmpty(params.get("password"))){
                SysUser sysUser = new SysUser();
                String username = (String) params.get("username");
                String password = (String) params.get("password");

                sysUser.setUsername(username);
                sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password)+username));

                SysUser sysUser1 = sysUserService.selectOne(sysUser);
                if (sysUser1!=null){
                    result.setMsg("登录成功");
                    result.setSuccess(true);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("username",username);
                    map.put("id",sysUser1.getId());
                    result.setObj(map);
                    //保存用户信息给前端使用
                    session.setAttribute("user",sysUser);

                }


            }
        }

        return result;
    }
}
