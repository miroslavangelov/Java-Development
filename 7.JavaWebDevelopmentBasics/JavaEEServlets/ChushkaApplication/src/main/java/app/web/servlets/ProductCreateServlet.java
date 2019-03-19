package app.web.servlets;

import app.domain.entities.Type;
import app.domain.models.ProductServiceModel;
import app.services.ProductService;
import app.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private final static String CREATE_PRODUCT_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ChushkaApplication\\src\\main\\resources\\views\\create-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String htmlContent = this.htmlReader.readHtmlFile(CREATE_PRODUCT_FILE_PATH)
                .replace("{{typeOptions}}", this.formatOptions());

        writer.println(htmlContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        String productType = req.getParameter("type");
        ProductServiceModel productServiceModel = new ProductServiceModel();

        productServiceModel.setName(productName);
        productServiceModel.setDescription(productDescription);
        productServiceModel.setType(productType);
        this.productService.saveProduct(productServiceModel);
        resp.sendRedirect(String.format("/products/details?name=%s", productServiceModel.getName()));
    }

    private String formatOptions() {
        StringBuilder result = new StringBuilder();

        Arrays.stream(Type.values())
                .forEach(type -> result.append(String.format(
                        "<option value=\"%s\">%s</option>",
                        type.name(),
                        type.name()
                )));

        return result.toString();
    }
}
