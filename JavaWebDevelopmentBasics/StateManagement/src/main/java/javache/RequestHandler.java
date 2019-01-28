package javache;

import db.User;
import javache.http.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static javache.WebConstants.*;

public class RequestHandler {
    private HttpSessionStorage sessionStorage;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public RequestHandler(HttpSessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
        this.httpResponse = new HttpResponseImpl();
        this.entityManagerFactory = Persistence.createEntityManagerFactory("case_book");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public byte[] handleRequest(String requestContent) throws IOException {
       this.httpRequest = new HttpRequestImpl(requestContent);

        byte[] result = null;

        if (this.httpRequest.getMethod().equals("GET")) {
            result = this.processGetRequest();
        } else if(this.httpRequest.getMethod().equals("POST")){
            result = this.processPostRequest();
        }

        return result;
    }

    private byte[] processPostRequest() throws IOException {
        String url = this.httpRequest.getRequestUrl();

        if (url.equals("/register")) {
            HashMap<String, String> bodyParameters = this.httpRequest.getBodyParameters();
            String email = bodyParameters.get("email");
            String password = bodyParameters.get("password");
            String confirmPassword = bodyParameters.get("confirmPassword");

            if (password.equals(confirmPassword)) {
                this.entityManager.getTransaction().begin();
                User user = new User();
                user.setEmail(URLDecoder.decode(email, "UTF-8"));
                user.setPassword(URLDecoder.decode(password, "UTF-8"));
                this.entityManager.persist(user);
                this.entityManager.getTransaction().commit();

                return this.processPageRequest("\\login");
            } else {
                return this.processPageRequest("\\register");
            }
        } else if (url.equals("/login")) {
            HashMap<String, String> bodyParameters = this.httpRequest.getBodyParameters();
            String email = URLDecoder.decode(bodyParameters.get("email"), "UTF-8");
            String password = URLDecoder.decode(bodyParameters.get("password"), "UTF-8");
            User user = this.entityManager
                    .createQuery("FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (user != null) {
                HttpSession httpSession = new HttpSessionImpl();
                httpSession.addAttribute("email", email);
                httpSession.addAttribute("password", password);
                this.sessionStorage.addSession(httpSession);
                this.httpResponse.addCookie("user", httpSession.getId());

                return this.dynamicUserPage(user.getId());
            }

            return this.processPageRequest("\\login");
        }
        return null;
    }

    private  byte[] dynamicUserPage(String userId) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(new File(PAGES_PATH + "/users/profile.html")));
        StringBuilder str = new StringBuilder();

        while(bf.ready()) {
            str.append((char) bf.read());
        }

        User user = this.entityManager.find(User.class, userId);
        int usernameIndex = str.indexOf("@");
        str.replace(usernameIndex, usernameIndex + 1, user.getEmail());
        int passwordIndex = str.indexOf("#");
        str.replace(passwordIndex,passwordIndex + 1, user.getPassword());
        byte[] result = str.toString().getBytes();

        return this.ok(result);
    }

    private byte[] processGetRequest() {
        String url = this.httpRequest.getRequestUrl();

        this.sessionStorage.refreshSessions();

        if (url.equals("/")) {
            return this.processPageRequest("/index");
        } else if (url.equals("/login")) {
            HashMap<String, HttpCookie> cookies = this.httpRequest.getCookies();
            return this.processPageRequest(url);
        } else if (url.equals("/register")) {
            return this.processPageRequest(url);
        } else if (url.equals("/users/profile")) {
            return this.processPageRequest(url);
        }

        return this.processResourceRequest();
    }

    private byte[] processPageRequest(String url) {
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
