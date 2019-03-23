package app.web.servlets;

import app.domain.models.ProductDetailsViewModel;
import app.domain.models.ProductServiceModel;
import app.services.ProductService;
import app.util.HtmlReader;
import app.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    private final static String PRODUCT_DETAILS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ChushkaApplication\\src\\main\\resources\\views\\product-details.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String productName = req.getQueryString().split("=")[1];
        ProductServiceModel productServiceModel  = this.productService.findProductByName(productName);
        ProductDetailsViewModel productDetailsViewModel = this.modelMapper.map(productServiceModel, ProductDetailsViewModel.class);

        String htmlContent = this.htmlReader.readHtmlFile(PRODUCT_DETAILS_FILE_PATH)
                .replace("{{productName}}", productDetailsViewModel.getName())
                .replace("{{productDescription}}", productDetailsViewModel.getDescription())
                .replace("{{productType}}", productDetailsViewModel.getType());

        writer.println(htmlContent);
    }
}
