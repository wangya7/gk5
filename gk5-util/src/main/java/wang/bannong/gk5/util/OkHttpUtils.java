package wang.bannong.gk5.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wang.bannong on 2018/8/3 下午2:16
 */
public final class OkHttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String get(String url) {
        return get(url, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, params, Collections.EMPTY_MAP);
    }

    public static String get(String url, Map<String, String> params, Map<String, String> paramsHeader) {
        StringBuffer sb = new StringBuffer(url);
        if (params != null && params.size() > 0) {
            sb.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() != null) {
                    sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            return getInvoke(sb.substring(0, s.length() - 1), paramsHeader);
        }
        return getInvoke(sb.toString(), paramsHeader);
    }


    public static String post(String url,String paramsJsonString) {
        Objects.requireNonNull(url, "url cannot be null");
        RequestBody body = RequestBody.create(JSON, paramsJsonString);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return ofResponse(request);
    }


    /**
     * Form表单提交
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url,Map<String, String> params) {
        Objects.requireNonNull(url, "url cannot be null");
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();

        return ofResponse(request);
    }


    public static String post(String url,Map<String, String> params, Map<String, String> paramsHeader) {
        Objects.requireNonNull(url, "url cannot be null");
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(paramsHeader))
                .post(formBodyBuilder.build())
                .build();

        return ofResponse(request);
    }




    private static String getInvoke(String url, Map<String, String> paramsHeader) {
        Objects.requireNonNull(url, "url cannot be null");
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(paramsHeader))
                .build();

        return ofResponse(request);
    }

    private static String ofResponse(Request request) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .build();
        String s = null;
        boolean flag = false;
        Response response = null;
        try {
            response = client.newCall(request).execute();
            flag = response.isSuccessful();
            s = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!flag) {
            throw new RuntimeException("Unexpected code " + response);
        }
        return s;
    }
}