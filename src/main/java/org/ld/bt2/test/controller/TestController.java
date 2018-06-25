package org.ld.bt2.test.controller;

import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public ResponseResult<String> zeroException() {
        SimpleDateFormat format = new SimpleDateFormat("YMD");
        String s = format.format("2018");
        return new ResponseResult<>(true, "", s);
    }
}
