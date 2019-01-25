package javache.handlers;

import javache.constants.HttpStatus;
import javache.http.HttpRequest;
import javache.http.HttpRequestImpl;
import javache.http.HttpResponse;
import javache.http.HttpResponseImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static javache.constants.WebConstants.INTERNAL_SERVER_ERROR_MESSAGE;

public class RequestHandler {
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\";
    private static final String PAGES_PATH = RESOURCES_PATH + "pages\\";
    private static final String ASSETS_PATH = RESOURCES_PATH + "assets\\";
    private static final Map<String, String> CONTENT_TYPES = new HashMap<>() {{
        put("html", "text/html; charset=utf-8");
        put("txt", "text/plain; charset=utf-8");
        put("png", "image/png");
        put("jpg", "image/jpeg");
        put("jpeg", "image/jpeg");
        put("css", "text/css; charset=utf-8");
    }};

    private HttpRequest httpRequest;
    private HttpResponse httpResponse;

    public RequestHandler() {
        this.httpResponse = new HttpResponseImpl();
    }

    public byte[] handleRequest(String requestContent) {
        this.httpRequest = new HttpRequestImpl(requestContent);

        byte[] result = null;

        if (this.httpRequest.getMethod().equals("GET")) {
            result = this.processGetRequest();
        }

        return result;
    }

    private byte[] processGetRequest() {
        String url = this.httpRequest.getRequestUrl();

        if (!this.httpRequest.isResource()) {
            return this.processPageRequest(url);
        }

        return this.processResourceRequest();
    }

    private byte[] processResourceRequest() {
        String assetPath = ASSETS_PATH + this.httpRequest.getRequestUrl();
        File file = new File(assetPath);

        if (!file.exists() || file.isDirectory()) {
            return this.notFound(("Asset not found!").getBytes());
        }

        byte[] result;

        try {
            result = Files.readAllBytes(Paths.get(assetPath));
        } catch (IOException e) {
            return this.internalServerError(INTERNAL_SERVER_ERROR_MESSAGE.getBytes());
        }

        this.httpResponse.addHeader("Content-Type", CONTENT_TYPES.get(this.getExtension(file)));
        this.httpResponse.addHeader("Content-Length", result.length + "");
        this.httpResponse.addHeader("Content-Disposition", "inline");

        this.httpResponse.addHeader("Cache-Control", "public,max-age=604800");
        this.httpResponse.addHeader("Accept-Ranges", "bytes");
        this.httpResponse.addHeader("X-Frame-Options", "deny");

        return this.ok(result);
    }

    private byte[] processPageRequest(String url) {
        if (url.equals("/")) {
            url = "index";
        }

        String pagePath = PAGES_PATH + url + ".html";
        File file = new File(pagePath);
        byte[] result;

        if (!file.exists() || file.isDirectory()) {
            try {
                result = Files.readAllBytes(Paths.get(PAGES_PATH + "not_found.html"));
                return this.notFound(result);
            } catch (IOException e) {
                return this.internalServerError(INTERNAL_SERVER_ERROR_MESSAGE.getBytes());
            }
        }

        try {
            result = Files.readAllBytes(Paths.get(pagePath));
        } catch (IOException e) {
            return this.internalServerError(INTERNAL_SERVER_ERROR_MESSAGE.getBytes());
        }

        this.httpResponse.addHeader("Content-Type", CONTENT_TYPES.get(this.getExtension(file)));

        return this.ok(result);
    }

    private byte[] ok(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.OK);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] badRequest(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] notFound(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] redirect(byte[] content, String location) {
        this.httpResponse.setStatusCode(HttpStatus.SEE_OTHER);
        this.httpResponse.addHeader("Location", location);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] internalServerError(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
