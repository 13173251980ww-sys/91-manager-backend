package com.amadeus.controller;

import com.amadeus.pojo.Result;
import com.amadeus.pojo.SysChicken;
import com.amadeus.pojo.SysUser;
import com.amadeus.service.NOSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NOSController {

    @Autowired
    private NOSService nosService;

    /**
     * 添加用户
     */
    @PostMapping("/user")
    public Result addUser(@RequestBody SysUser user) {
        try {
            nosService.addUser(user);
            return Result.success("用户添加成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加鸡
     */
    @PostMapping("/ck")
    public Result addCk(@RequestParam Integer account, @RequestBody SysChicken ck) {
        try {
            nosService.addCk(account, ck);
            return Result.success("鸡添加成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/user")
    public Result deleteUser(@RequestParam Integer account) {
        try {
            nosService.deleteUser(account);
            return Result.success("用户删除成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除鸡
     */
    @DeleteMapping("/ck")
    public Result deleteCk(@RequestParam Integer account) {
        try {
            nosService.deleteCk(account);
            return Result.success("鸡删除成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 用户修改信息
     */
    @PutMapping("/user")
    public Result updateUser(@RequestBody SysUser user) {
        try {
            nosService.updateUser(user.getUser_account(), user);
            return Result.success("用户信息修改成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


    /**
     * 鸡修改信息
     */
    @PutMapping("/ck")
    public Result updateCk(@RequestParam Integer account, @RequestBody SysChicken ck) {
        try {
            nosService.updateCk(account, ck);
            return Result.success("鸡信息修改成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


    /**
     * 查询所有用户
     */
    @GetMapping("/user")
    public Result getUser() {
        try {
            List<SysUser> userList = nosService.getUser();
            return Result.success(userList);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 根据账号查询用户
     */
    @GetMapping("/user/{account}")
    public Result getUserById(@PathVariable Integer account) {
        SysUser user;
        try {
            user = nosService.getUserById(account);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


    /**
     * 查询所有鸡
     */
    @GetMapping("/ck")
    public Result getCk() {
        List<SysChicken> ckList = nosService.getCk();
        return Result.success(ckList);
    } //测试通过

    /**
     * 根据账号查询鸡
     */
    @GetMapping("/ck/{account}")
    public Result getCkById(@PathVariable Integer account) {
        try {
            SysChicken ck = nosService.getCkById(account);
            return Result.success(ck);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
