package app;

import app.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls();
        String request = getRequest();
        HttpRequest httpRequest = new HttpRequestImpl(request);
        HttpResponse httpResponse = new HttpResponseImpl();
        String method = httpRequest.getMethod();
        String url = httpRequest.getRequestUrl();
        Map<String, String> headers = httpRequest.getHeaders();
        Map<String, String> bodyParams = httpRequest.getBodyParameters();
        StringBuilder response = new StringBuilder();

        headers.forEach((key, value) -> {
            if (key.equals("Date") ||
                    key.equals("Host") ||
                    key.equals("Content-Type")) {
                httpResponse.addHeader(key, value);
            }
        });

        if (!validUrls.contains(url)) {
            httpResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            response.append(new String(httpResponse.getBytes(), StandardCharsets.UTF_8));
            response.append("The requested functionality was not found.");
        } else if (!headers.keySet().contains("Authorization")) {
            httpResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            response.append(new String(httpResponse.getBytes(), StandardCharsets.UTF_8));
            response.append("You are not authorized to access the requested functionality.");
        } else if (method.equals("POST") && bodyParams.size() == 0) {
            httpResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.append(new String(httpResponse.getBytes(), StandardCharsets.UTF_8));
            response.append("There was an error with the requested functionality due to malformed request.");
        } else {
            httpResponse.setHttpStatus(HttpStatus.OK);
            response.append(new String(httpResponse.getBytes(), StandardCharsets.UTF_8));

            String first = "";
            String second = "";
            String third = "";
            int count = 0;
            for (Map.Entry<String, String> bodyParam : bodyParams.entrySet()) {
                switch (count) {
                    case 0:
                        first = bodyParam.getValue();
                        break;
                    case 1:
                        second = bodyParam.getKey() + " - " + bodyParam.getValue();
                        break;
                    case 2:
                        third = bodyParam.getKey() + " - " + bodyParam.getValue();
                        break;
                }

                count++;
            }

            String responseBody = String.format(
                    "Greetings %s! You have successfully created %s with %s, %s.",
                    new String(Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1])),
                    first,
                    second,
                    third
            );

            response.append(responseBody);
        }

        System.out.println(response);
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static String getRequest() throws IOException {
        StringBuilder request = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null && line.length() > 0) {
            request.append(line).append(System.lineSeparator());
        }

        request.append(System.lineSeparator());

        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.append(line);
        }

        return request.toString();
    }
}
