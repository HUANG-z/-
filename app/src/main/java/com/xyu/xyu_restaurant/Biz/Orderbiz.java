package com.xyu.xyu_restaurant.Biz;
import android.util.Log;

import com.xyu.xyu_restaurant.Bean.Order;
import com.xyu.xyu_restaurant.Config.Config;
import com.xyu.xyu_restaurant.Net.CommonCallBack;
import com.xyu.xyu_restaurant.UI.Activity.OrderAddlist;
import com.xyu.xyu_restaurant.UserInfoViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
//订单业务类
public class Orderbiz {
    //订单查询
    public void order_list(String username,int currentPage,CommonCallBack<List<Order>> callBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+Config.url+"order_find")
                .addParams("username",username)
                .addParams("currentPage",currentPage+"")
                .tag(this)
                .build()
                .execute(callBack);
    }

//添加订单
    public void order_add(List<OrderAddlist> list, CommonCallBack<String> callBack){
        StringBuilder sb_name=new StringBuilder();
        StringBuilder sb_number=new StringBuilder();
        for(OrderAddlist p : list){
          sb_name.append(p.getGoods_name());
          sb_name.append("|");
          sb_number.append(p.getNumber());
            sb_number.append("|");
        }
        sb_number=sb_number.deleteCharAt(sb_number.length()-1);
        sb_name=sb_name.deleteCharAt(sb_name.length()-1);


        OkHttpUtils
                .post()
                .url(Config.baseUrl +Config.url+ "order_add")
                .addParams("username", UserInfoViewHolder.getInstance().getUser().getUsername())
                .addParams("goods_name", sb_name.toString())
                .addParams("number", sb_number.toString())
                .tag(this)
                .build()
                .execute(callBack);
    }

}
