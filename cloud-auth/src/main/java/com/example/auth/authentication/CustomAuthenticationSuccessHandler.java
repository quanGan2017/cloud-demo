package com.example.auth.authentication;

import com.alibaba.fastjson.JSON;
import com.example.cloudcomm.base.AppResult;
import com.example.cloudcomm.util.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = JwtTokenUtil.generateToken(user);
        response.setContentType("text/json;charset=utf-8");
        AppResult ar = AppResult.success(token);
        String str = JSON.toJSONString(ar);
        response.getWriter().write(JSON.toJSONString(AppResult.success(token)));
    }
}
