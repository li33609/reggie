package com.yahaha.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yahaha.reggie.common.R;
import com.yahaha.reggie.entity.User;
import com.yahaha.reggie.service.UserService;
import com.yahaha.reggie.utils.SMSUtils;
import com.yahaha.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yahaha
 * @date 2022-08-24 15:22:31
 * @description
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证码短信
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            // 生成4位随机验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);

            // 调用阿里云API发送短信
//            SMSUtils.sendMessage("", "", phone, code);

            // 需要将生成的验证码保存到Session
//            session.setAttribute(phone, code);

            // 将生成的验证码缓存进redis中,并设置5分钟有效期
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);


            return R.success("手机验证码短信发送成功");
        }

        return R.error("短信发送失败");
    }

    /**
     * 移动端用户登陆
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        log.info(map.toString());

        // 获取手机号
        String phone = map.get("phone").toString();

        // 获取验证码
        String code = map.get("code").toString();

        // 从session获取保存的验证码进行比对
//        Object codeInSession = session.getAttribute(phone);

        // 从redis中来获取缓存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        // 进行验证码的比对（页面提交的和session中保存的）
        if (codeInSession != null && codeInSession.equals(code)){
            // 如果对比成功，登陆成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);

            User user = userService.getOne(queryWrapper);
            if (user == null){
                // 判断当前手机号对应的用户是否为新用户，如果是则自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());

            // 登陆成功，删除redis中的验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }

        return R.error("短信发送失败");
    }

}
