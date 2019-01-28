package app.http;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {
    private Map<String, String> headers;
    private HttpStatus httpStatus;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new HashMap<>();
        this.setContent(new byte[0]);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getBytes() {
        byte[] headersBytes = this.getHeadersBytes();
        byte[] bodyBytes = this.getContent();

        byte[] fullResponse = new byte[headersBytes.length + bodyBytes.length];

        System.arraycopy(headersBytes, 0, fullResponse, 0, headersBytes.length);
        System.arraycopy(bodyBytes, 0, fullResponse, headersBytes.length, bodyBytes.length);

        return fullResponse;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    private byte[] getHeadersBytes() {
        StringBuilder headers = new StringBuilder();
        headers.append(String.format("HTTP/1.1 %d %s", this.getHttpStatus().getCode(), this.getHttpStatus().getName()))
                .append(System.lineSeparator());
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            headers.append(entry.getKey()).append(": ").append(entry.getValue()).append(System.lineSeparator());
        }
        headers.append(System.lineSeparator());

        return headers.toString().getBytes();
    }
}
