package app.web.servlets;

import app.domain.entities.Cat;
import app.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
    private final static String CAT_PROFILE_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\FluffyDuffyMunchkinCats\\src\\main\\resources\\views\\cat-profile.html";
    private final static String CAT_NOT_FOUND_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\FluffyDuffyMunchkinCats\\src\\main\\resources\\views\\cat-not-found.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String catName = req.getQueryString().split("=")[1];
        Cat cat = ((Map<String, Cat>)req.getSession().getAttribute("cats")).get(catName);
        String htmlContent;

        if (cat == null) {
            htmlContent = this.htmlReader.readHtmlFile(CAT_NOT_FOUND_FILE_PATH)
                .replace("{{catName}}", catName);
        } else {
            htmlContent = this.htmlReader.readHtmlFile(CAT_PROFILE_FILE_PATH)
                .replace("{{catName}}", cat.getName())
                .replace("{{catBreed}}", cat.getBreed())
                .replace("{{catColor}}", cat.getColor())
                .replace("{{numberOfLegs}}", cat.getNumberOfLegs().toString());
        }

        writer.println(htmlContent);
    }
}
