package com.amadeus.controller;

import com.amadeus.pojo.PageResult;
import com.amadeus.pojo.Result;
import com.amadeus.service.NOSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/users")
@RestController
public class PageController {

    @Autowired
    private NOSService nosService;

    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize) {
      log.info("分页查询: page={}, pageSize={}", page, pageSize);
      PageResult pageResult = nosService.page(page, pageSize);
      return Result.success(pageResult);
    }
}
