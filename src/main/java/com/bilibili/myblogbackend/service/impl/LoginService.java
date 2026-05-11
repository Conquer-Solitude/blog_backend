package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bilibili.myblogbackend.convertor.ClassConvertor;
import com.bilibili.myblogbackend.dto.UserDto;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.LoginUser;
import com.bilibili.myblogbackend.dto.vo.UserVO;
import com.bilibili.myblogbackend.mapper.UserMapper;
import com.bilibili.myblogbackend.service.ILoginService;
import com.bilibili.myblogbackend.util.JWTUtils;
import java.util.Objects;
import lombok.Generated;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService
implements ILoginService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ClassConvertor classConvertor;

    public UserDto login(UserVO user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) {
            throw new BadCredentialsException("身份校验失败！");
        } else {
            LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
            String token = JWTUtils.createToken(loginUser.getUser().getId());
            return this.classConvertor.toUserDto(loginUser.getUser(), token);
        }
    }

    public String register(UserVO userVO) {
        if (this.getUserByEmail(userVO.getEmail()) != null) {
            return "用户已存在";
        } else {
            this.userMapper.insert(this.convertUser(userVO));
            return "注册成功";
        }
    }

    private User getUserByEmail(String email) {
        return (User)this.userMapper.selectOne((Wrapper)(new QueryWrapper()).eq("email", email));
    }

    private User convertUser(UserVO userVO) {
        User user = this.classConvertor.toUser(userVO);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Generated
    public LoginService(final AuthenticationManager authenticationManager, final UserMapper userMapper, final PasswordEncoder passwordEncoder, final RedisTemplate<String, Object> redisTemplate) {
        this.classConvertor = ClassConvertor.INSTANCE;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
    }
}
