package javache;

import java.util.HashMap;
import java.util.Map;

public final class WebConstants {
    public static final Integer DEFAULT_SERVER_PORT = 8000;

    public static final String SERVER_HTTP_VERSION = "HTTP/1.1";

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong!";

    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\";

    public static final String PAGES_PATH = RESOURCES_PATH + "pages\\";

    public static final String ASSETS_PATH = RESOURCES_PATH + "assets\\";

    public static final Map<String, String> CONTENT_TYPES = new HashMap<>() {{
        put("html", "text/html; charset=utf-8");
        put("txt", "text/plain; charset=utf-8");
        put("png", "image/png");
        put("jpg", "image/jpeg");
        put("jpeg", "image/jpeg");
        put("css", "text/css; charset=utf-8");
    }};

    private WebConstants() { }
}
