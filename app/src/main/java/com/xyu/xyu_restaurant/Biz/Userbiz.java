package com.xyu.xyu_restaurant.Biz;
import com.xyu.xyu_restaurant.Bean.User;
import com.xyu.xyu_restaurant.Config.Config;
import com.xyu.xyu_restaurant.Net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
//用户业务类
public class Userbiz {
    //注册
    public   void Register(String username,String password , CommonCallBack<User> commonCallBack){
        OkHttpUtils.post()
                .url(Config.baseUrl+Config.url+"user_register")
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);
    }
    //登录
    public void Login(String username, String password, CommonCallBack<User> commonCallBack){
        OkHttpUtils.post()
                .url(Config.baseUrl+Config.url+"user_login")
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);
    }
}
