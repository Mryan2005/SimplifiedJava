package top.mryan2005.simplifiedjava.flarum;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CreateDiscussion {

    private String title;
    private String content;
    private String[] tags;
    private String token;
    private String hostUrl;

    public CreateDiscussion(String hostUrl, String token, String title, String content, String[] tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.token = token;
        this.hostUrl = hostUrl;
    }

    public StringBuffer submit() throws IOException {
        // URL of the server endpoint
        URL url = new URL(hostUrl + "/api/discussions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to POST
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set request headers
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Token " + this.token);

        // JSON payload
        String jsonInputString = String.format(
            "{" +
                "\"data\": {" +
                    "\"type\": \"discussions\"," +
                    "\"attributes\": {" +
                        "\"title\": \"%s\"," +
                        "\"content\": \"%s\"" +
                    "}," +
                    "\"relationships\": {" +
                        "\"tags\": {" +
                            "\"data\": [" +
                                "%s" +
                            "]" +
                        "}" +
                    "}" +
                "}" +
            "}",
            title, content, String.join(",", tags)
        );

        // Send the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Handle the response (optional)
         InputStream inputStream = connection.getInputStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
         String line;
         StringBuffer response = new StringBuffer();
         while ((line = reader.readLine()) != null) {
             response.append(line);
         }
         reader.close();
         return response;
    }

    public static void main(String[] args) throws IOException {
        String hostUrl = "https://xxx.com";
        String token = "xxx";
        String title = "Hello World";
        String content = "This is a test discussion";
        String[] tags = new String[]{"{\"type\": \"tags\", \"id\": \"19\"}"};
        CreateDiscussion createDiscussion = new CreateDiscussion(
            hostUrl, token, title, content, tags
        );
        createDiscussion.submit();
    }
}
