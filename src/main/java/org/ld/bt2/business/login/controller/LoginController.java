package org.ld.bt2.business.login.controller;

import org.ld.bt2.business.account.model.AccountModel;
import org.ld.bt2.business.account.service.AccountService;
import org.ld.bt2.config.shiro.jwt.JWTUtil;
import org.ld.bt2.config.shiro.token.JWTToken;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult<String> login(@RequestBody AccountModel model,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        ResponseResult<AccountModel> result = service.findByAccount(model.getAccount());
        if (result.isSuccess()) {
            String sign = JWTUtil.sign(result.getData().getAccount(), result.getData().getPassword());
            JWTToken token = new JWTToken(sign);
            getSubject().login(token);
//            response.setHeader("Authorization", sign);
            return new ResponseResult<>(true, "登录成功", null);
        } else
            return new ResponseResult<>(false, result.getMessage(), null);
    }
}
