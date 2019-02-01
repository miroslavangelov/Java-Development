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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateCatServlet extends HttpServlet {
    private final static String CREATE_CAT_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\FluffyDuffyMunchkinCats\\src\\main\\resources\\views\\create-cat.html";

    private final HtmlReader htmlReader;

    @Inject
    public CreateCatServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println(this.htmlReader.readHtmlFile(CREATE_CAT_FILE_PATH));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getParameter("name");
        String catBreed = req.getParameter("breed");
        String catColor = req.getParameter("color");
        Integer numberOfLegs = Integer.parseInt(req.getParameter("numberOfLegs"));
        Cat cat = new Cat(catName, catBreed, catColor, numberOfLegs);

        if (req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new HashMap<>());
        }

        ((Map<String,Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);
        resp.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));
    }
}
