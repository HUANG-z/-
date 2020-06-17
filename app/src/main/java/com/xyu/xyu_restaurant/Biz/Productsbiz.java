package com.xyu.xyu_restaurant.Biz;

import com.xyu.xyu_restaurant.Bean.Products;
import com.xyu.xyu_restaurant.Config.Config;
import com.xyu.xyu_restaurant.Net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

//商品业务类
public class Productsbiz {
    public void listByPage(int currentPage, CommonCallBack<List<Products>> callback) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + Config.url+"product_find")
                .tag(this)
                .addParams("currentPage", currentPage + "")
                .build()
                .execute(callback);
    }
}
