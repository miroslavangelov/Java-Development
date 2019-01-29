package app.http;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {
    private String method;
    private String requestUrl;
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private List<HttpCookie> cookies;

    public HttpRequestImpl(String requestContent) {
        this.initMethod(requestContent);
        this.initRequestUrl(requestContent);
        this.initHeaders(requestContent);
        this.initBodyParameters(requestContent);
        this.initCookies();
    }

    private void initMethod(String requestContent) {
        this.setMethod(requestContent.split("\\s")[0]);
    }

    private void initRequestUrl(String requestContent) {
        this.setRequestUrl(requestContent.split("\\s")[1]);
    }

    private void initHeaders(String requestContent) {
        this.headers = new HashMap<>();

        List<String> requestParams = Arrays.asList(
                requestContent.split("\\r\\n"));

        int i = 1;

        while(i < requestParams.size() && requestParams.get(i).length() > 0) {
            String[] headerKeyValuePair = requestParams.get(i).split(":\\s");

            this.addHeader(headerKeyValuePair[0], headerKeyValuePair[1]);
            i++;
        }
    }

    private void initBodyParameters(String requestContent) {
        if (this.getMethod().equals("POST")) {
            this.bodyParameters = new HashMap<>();
            List<String> requestParams = Arrays.asList(requestContent.split("\\r\\n"));

            if (requestParams.size() > this.headers.size() + 2) {
                List<String> bodyParams = Arrays.asList(requestParams.get(this.headers.size() + 2).split("&"));

                for (String bodyParam: bodyParams) {
                    String[] bodyKeyValuePair = bodyParam.split("=");

                    this.addBodyParameter(bodyKeyValuePair[0], bodyKeyValuePair[1]);
                }
            }
        }
    }

    private void initCookies() {
        this.cookies = new ArrayList<>();

        if (!this.headers.containsKey("Cookie")) {
            return;
        }

        String cookiesHeader = this.headers.get("Cookie");
        String[] allCookies = cookiesHeader.split(";\\s");

        for (String cookie: allCookies) {
            String[] cookieNameValuePair = cookie.split("=");

            this.cookies.add(new HttpCookieImpl(cookieNameValuePair[0], cookieNameValuePair[1]));
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return Collections.unmodifiableList(this.cookies);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl = requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.putIfAbsent(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.getRequestUrl().contains(".");
    }
}
