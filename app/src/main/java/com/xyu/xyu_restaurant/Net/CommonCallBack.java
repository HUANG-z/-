package com.xyu.xyu_restaurant.Net;
import com.xyu.xyu_restaurant.Utils.GsonUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;
public abstract class CommonCallBack<T> extends StringCallback {
    Type mtype;

    public CommonCallBack() {
        //获取被extends 的类
        Class<? extends CommonCallBack> clazz=getClass();
        Type genericSupperclass=clazz.getGenericSuperclass();
        if(genericSupperclass instanceof  Class){
            throw  new RuntimeException(" Miss Type Params");
        }
        ParameterizedType parameterizedType= (ParameterizedType) genericSupperclass;
        //getActulTypeArguments 是一个T对象（泛型，所有是数组）
        mtype=parameterizedType.getActualTypeArguments()[0];
    }
    @Override
    public void onError(Call call, Exception e, int id) {
      onError(e);
    }

    @Override
    public void onResponse(String response, int id) {
        try {// 转化为json对象
            JSONObject jsonObject=new JSONObject(response);
            int resCode=jsonObject.getInt("resultCode");
            if(resCode==1){
             //成功
                String data=jsonObject.getString("data");
                //GsonUtils 封装的Gson对象
               onSuccess((T) GsonUtils.getgson().fromJson(data,mtype));
            }else {
              //请求失败
                onError(new RuntimeException(jsonObject.getString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(e);
        }
    }
   public abstract  void onError(Exception e);
   public abstract void onSuccess(T response);
}
