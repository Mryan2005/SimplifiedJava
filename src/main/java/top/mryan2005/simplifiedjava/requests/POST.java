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

public class POST {
    public Map headers = new HashMap<String, String>();
    public Map body = new HashMap<String, String>();
    public String URL;
    HttpClient client;
    public HttpRequest request;

    public POST(String url) {
        client = HttpClient.newHttpClient();
        URL = url;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addBody(String key, String value) {
        body.put(key, value);
    }

    public void removeHeader(String key) {
        headers.remove(key);
    }

    public void removeItemInBody(String key) {
        body.remove(key);
    }

    public void clearHeaders() {
        headers.clear();
    }

    public void clearBody() {
        body.clear();
    }

    public HttpResponse<String> sendRequest() throws IOException, InterruptedException {
        var objectMapper = new ObjectMapper();
        if(headers.size() == 0) {
            String requestBody = objectMapper
                    .writeValueAsString(body);
            request= HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        if (body.size() == 0) {
            ArrayList<String> headerArray = new ArrayList<>();
            for (Object key : this.headers.keySet()) {
                headerArray.add((String) key);
                headerArray.add((String) this.headers.get(key));
            }
            request= HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .headers(headerArray.toArray(new String[0]))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        ArrayList<String> headerArray = new ArrayList<>();
        for (Object key : this.headers.keySet()) {
            headerArray.add((String) key);
            headerArray.add((String) this.headers.get(key));
        }
        request= HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .headers(headerArray.toArray(new String[0]))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendRequest(String url, Map headersInput, Map bodyInput) throws IOException, InterruptedException {
        var objectMapper = new ObjectMapper();
        ArrayList<String> headerArray = new ArrayList<>();
        for (Object key : headersInput.keySet()) {
            headerArray.add((String) key);
            headerArray.add((String) headersInput.get(key));
        }
        request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(headerArray.toArray(new String[0]))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(bodyInput)))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendRequest(String url, Map bodyInput) throws IOException, InterruptedException {
        var objectMapper = new ObjectMapper();
        request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(bodyInput)))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendRequest(String url) throws IOException, InterruptedException {
        request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        POST post = new POST("https://httpbin.org/post");
        post.addHeader("Content-Type", "application/json");
        post.addBody("key", "value");
        HttpResponse<String> res = post.sendRequest();
        System.out.println(res.body());
    }
}
