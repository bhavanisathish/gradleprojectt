package com.example.allinone.utilis;

import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

public class HttpUtilis {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");;
    @Autowired
    private ResponseUtilis responseUtilis;

    public String post(String url,String json)throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request req = new Request.Builder()
                        .url(url)
                           .post(body)
                            .build();
        Response res = client.newCall(req).execute();
        return res.body().string();
    }
    public String gett(String url,String json)throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response res = client.newCall(req).execute();
        return res.body().string();
    }

}
