package top.mryan2005.simplifiedjava.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import top.mryan2005.simplifiedjava.Hash;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GET {
    public Map headers = new HashMap<String, String>();
    public String URL;
    HttpClient client;
    public HttpRequest request;

    public GET(String url) {
        client = HttpClient.newHttpClient();
        URL = url;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void removeHeader(String key) {
        headers.remove(key);
    }

    public void clearHeaders() {
        headers.clear();
    }

    public HttpResponse<String> sendRequest() throws IOException, InterruptedException {
        ArrayList<String> headerArray = new ArrayList<>();
        for (Object key : this.headers.keySet()) {
            headerArray.add((String) key);
            headerArray.add((String) this.headers.get(key));
        }
        request= HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .headers(headerArray.toArray(new String[0]))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendRequest(String url, Map headersInput) throws IOException, InterruptedException {
        ArrayList<String> headerArray = new ArrayList<>();
        for (Object key : headersInput.keySet()) {
            headerArray.add((String) key);
            headerArray.add((String) headersInput.get(key));
        }
        request= HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .headers(headerArray.toArray(new String[0]))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GET get = new GET("https://httpbin.org/GET");
        get.addHeader("Content-Type", "application/json");
        HttpResponse<String> res = get.sendRequest();
        System.out.println(res.body());
    }
}
