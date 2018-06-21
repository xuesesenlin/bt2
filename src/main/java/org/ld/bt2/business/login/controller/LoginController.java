package org.ld.bt2.business.login.controller;

import org.ld.bt2.business.account.model.AccountModel;
import org.ld.bt2.business.account.service.AccountService;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult<String> login(@RequestBody AccountModel model) {
        ResponseResult<AccountModel> result = service.findByAccount(model.getAccount());

        if (result.isSuccess())
            return new ResponseResult<>(true, "登录成功", null);
        else
            return new ResponseResult<>(false, result.getMessage(), null);
    }
}
