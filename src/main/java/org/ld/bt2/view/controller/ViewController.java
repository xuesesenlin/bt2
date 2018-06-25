package org.ld.bt2.view.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/view")
public class ViewController {

    //    统一页面跳转
    @RequestMapping(value = "/{html}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("html") String html) {
        return new ModelAndView("/" + html);
    }
}
