package org.ld.bt2.business.login.controller;

import org.ld.bt2.business.account.model.AccountModel;
import org.ld.bt2.business.account.service.AccountService;
import org.ld.bt2.config.shiro.jwt.JWTUtil;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
                                        HttpServletResponse response) {
        ResponseResult<AccountModel> result = service.findByAccount(model.getAccount());
        if (result.isSuccess()) {
            if (model.getAccount().equals(model.getPassword())) {
                String sign = JWTUtil.sign(result.getData().getAccount(), result.getData().getPassword());
                response.setHeader("Authorization", sign);
                return new ResponseResult<>(true, "登录成功", null);
            } else
                return new ResponseResult<>(false, "账号密码错误", null);
        } else
            return new ResponseResult<>(false, result.getMessage(), null);
    }
}
