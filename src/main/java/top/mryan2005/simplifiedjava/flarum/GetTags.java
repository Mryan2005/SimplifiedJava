package top.mryan2005.simplifiedjava.flarum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTags {
    private String hostUrl;

    public GetTags(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public StringBuffer getTags() throws IOException {

        // URL of the server endpoint
        URL url = new URL(this.hostUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set request headers
        connection.setRequestProperty("Accept", "application/json");

        // Get the response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response;
    }

    public static void main(String[] args) throws IOException {
        GetTags GetTags1 = new GetTags("https://xxxx.com/api/tags?include=parent");
        System.out.println(GetTags1.getTags());
    }
}
