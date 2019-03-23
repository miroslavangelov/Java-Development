package app.web.servlets;

import app.domain.entities.Cat;
import app.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CatAllServlet extends HttpServlet {
    private final static String ALL_CATS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\FluffyDuffyMunchkinCats\\src\\main\\resources\\views\\all-cats.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Map<String, Cat> cats = ((Map<String, Cat>)req.getSession().getAttribute("cats"));
        String htmlContent = this.htmlReader.readHtmlFile(ALL_CATS_FILE_PATH);

        if (cats == null) {
            htmlContent = htmlContent.replace(
                            "{{allCats}}",
                            "<h2>There are no cats.</h2><br /><a href=\"/cats/create\">Create some!</a><br />"
                    );
        } else {
            StringBuilder allCatsResult = new StringBuilder();

            cats.values().forEach(cat -> allCatsResult.append(String.format(
                    "<a href=\"/cats/profile?catName=%s\">%s</a><br />",
                    cat.getName(), cat.getName())
            ));
            htmlContent = htmlContent.replace(
                    "{{allCats}}",
                    allCatsResult.toString()
            );
        }

        writer.println(htmlContent);
    }
}
