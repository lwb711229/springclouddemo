package com.gcx.control;


import com.gcx.utils.JwtTokenUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by echisan on 2018/6/23
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {


/*

    @PostMapping("/success")
    public Rest loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        SysUser sysUser = sysUserService.queryByUsername(username);
        // 脱敏
        sysUser.setEncodePassword("[PROTECT]");
        return RestBody.okData(sysUser,"登录成功");
    }
*/

    @GetMapping
    public String listTasks(HttpServletRequest request, HttpServletResponse response){
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
       String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<GrantedAuthority> role = JwtTokenUtils.getUserRole(token);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object user = authentication.getAuthorities();




        return "任务列表"+username+"==="+role.toString();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String newTasks(){
        return "创建了一个新的任务";
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId")Integer id){
        return "更新了一下id为:"+id+"的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId")Integer id){
        return "删除了id为:"+id+"的任务";
    }

    @GetMapping("/test1")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String test1(HttpServletRequest request, HttpServletResponse response){
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<GrantedAuthority> role = JwtTokenUtils.getUserRole(token);
        return "任务列表test1"+username+"==="+role.toString();
    }
    @GetMapping("/test2")
    @PreAuthorize("hasRole('ROLE_USER2')")
    public String test2(HttpServletRequest request, HttpServletResponse response){
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<GrantedAuthority> role = JwtTokenUtils.getUserRole(token);
        return "任务列表test2"+username+"==="+role.toString();
    }

    @GetMapping("/test3")
    @PreAuthorize("hasRole('ROLE_USER3')")
    public String test3(HttpServletRequest request, HttpServletResponse response){
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<GrantedAuthority> role = JwtTokenUtils.getUserRole(token);
        return "任务列表test3"+username+"==="+role.toString();
    }

    @GetMapping("/test4")

    public String test4(HttpServletRequest request, HttpServletResponse response){
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<GrantedAuthority> role = JwtTokenUtils.getUserRole(token);
        return "任务列表test4"+username+"==="+role.toString();
    }

}
