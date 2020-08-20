package com.gcx.entity;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by echisan on 2018/6/23
 */
public class JwtUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(User user) {
        /*id = user.getId();
        username = user.getUsername();
        password = user.getPassword();*/


        id = 10;
        username = "admin";
        password = "$2a$10$AR/jNSqvnJB.CC5Q30VlQ.UrRcebdsXYL9qguCu/SY16n.7Ec8Jl6";
        //authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
        String role1="ROLE_USER";
        String role2="ROLE_USER2";
        GrantedAuthority authority1=new SimpleGrantedAuthority(role1);
        GrantedAuthority authority2=new SimpleGrantedAuthority(role2);
        List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
        list.add(authority1);
        list.add(authority2);
        authorities = list;

        //spring security提供了逗号工具类，可以将逗号格式的字符串代替上面的方法，创建一个List<GrantedAuthority>,AuthorityUtils.commaSeparatedStringToAuthorityList
        //推荐将权限用逗号隔开的方式存到user表中，而不是单独的一个user和权限绑定的map表


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
