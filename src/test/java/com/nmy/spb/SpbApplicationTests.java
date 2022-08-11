package com.nmy.spb;

import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpbApplicationTests {


//    @Test
//    void poweredByAwaitility() throws NoSuchAlgorithmException, InterruptedException {
//        System.out.println("开始执行");
//        long date = System.currentTimeMillis();
//        int num = (int) (Math.random() * 10000);
//        String aaaa = "nCZrj6SCU1h0" + num + date;
//        String string;
//        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                'a', 'b', 'c', 'd', 'e', 'f'};
//        try {
//            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
//            mdTemp.update(aaaa.getBytes("UTF-8"));
//            byte[] md = mdTemp.digest();
//            int j = md.length;
//            char buf[] = new char[j * 2];
//            int k = 0;
//            for (int i = 0; i < j; i++) {
//                byte byte0 = md[i];
//                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                buf[k++] = hexDigits[byte0 & 0xf];
//            }
//            string = new String(buf);
//            System.out.println(string);
//        } catch (Exception e) {
//            string = "";
//        }
//        System.out.println("时间" + date + "随机数" + num);
//        RequestBody requestBody = new FormBody.Builder()
//                .add("userId", "sada213")
//                .add("name", "hahahahaha")
//                .add("portraitUri", "_")
//                .build();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .build();
//        Request request = new Request.Builder()
//                .url("https://api-cn.ronghub.com/user/getToken.json")
//                .header("App-Key", "x18ywvqfx4z8c")
//                .header("Nonce", String.valueOf(num))
//                .header("Timestamp", String.valueOf(date))
//                .header("Signature", string)
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .post(requestBody)
//                .build();
//        System.out.println("开始发送");
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("执行错误");
//                System.out.println(call.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("执行成功");
//                if (response == null) {
//                    System.out.println("空");
//                }
//                System.out.println(response);
//                System.out.println(response.body().string());
//            }
//
//        });
//        Thread.sleep(4000);
//    }
    @Autowired
    PasswordEncoder pas;
    @Test
    public void aaa(){
        System.out.println(pas.encode("11111111"));
    }
}
