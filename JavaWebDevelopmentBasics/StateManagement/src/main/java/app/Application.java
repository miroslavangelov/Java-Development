package app;

import app.http.HttpRequest;
import app.http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String request = getRequest();
        HttpRequest httpRequest = new HttpRequestImpl(request);

        httpRequest.getCookies().forEach(cookie -> System.out.println(
                String.format("%s <-> %s", cookie.getKey(), cookie.getValue()))
        );
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
