package com.pinyougou;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/****
 *author:Lenovo 11:19
 *description:深圳黑马
 *version:1.0

 ****/
//认证类，从数据库读取数据
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    //调用sellerService以便拿到商家对象
    private SellerService sellerService;
    //通过setter方法注入,要在配置文件中通过<beans:bean>注入
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //username参数就是用户输入的用户名
        //构建角色列表
        List<GrantedAuthority> grantAuths=new ArrayList<GrantedAuthority>();
        grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //获取商家对象
        TbSeller seller = sellerService.findOne(username);
        if(seller!=null){
            if(seller.getStatus().equals("1")){
                return new User(username,seller.getPassword(),grantAuths);
            }
            return null;
        }
        return null;

        //返回用户对象，当用户输入的用户名和密码与返回的相同时进行登录
    }
}
