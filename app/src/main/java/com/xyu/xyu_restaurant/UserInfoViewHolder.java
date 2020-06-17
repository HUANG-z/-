package com.xyu.xyu_restaurant;

import com.xyu.xyu_restaurant.Bean.User;
import com.xyu.xyu_restaurant.Utils.SPUtils;

public class UserInfoViewHolder {
    private static UserInfoViewHolder userInfoViewHolder=new UserInfoViewHolder();
    private User user;
    private static  final String KEY_USERNAME="key_username";
    public static UserInfoViewHolder getInstance(){
        return  userInfoViewHolder;
    }
    public User getUser() {
        //从内存中获取持久化的user
        User u=user;
        if(u==null){
            String username= (String) SPUtils.getInstance().get(KEY_USERNAME,"");
            u.setUsername(username);
        }
        //更新内存中的user
        user=u;
        return u;
    }

    public void setUser(User user) {
        this.user = user;
        if(user!=null){
            //保存一个用户名即可
            SPUtils.getInstance().put(KEY_USERNAME,user.getUsername());
        }
    }

}
