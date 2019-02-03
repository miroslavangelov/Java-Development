package app.web.servlets;

import app.domain.models.AllProductsViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final static String INDEX_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ChushkaApplication\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<AllProductsViewModel> products = this.productService.findAllProducts()
                .stream()
                .map(productServiceModel -> this.modelMapper.map(productServiceModel, AllProductsViewModel.class))
                .collect(Collectors.toList());
        String htmlContent = this.htmlReader.readHtmlFile(INDEX_FILE_PATH)
                .replace("{{allProducts}}", this.formatProducts(products));

        writer.println(htmlContent);
    }

    private String formatProducts(List<AllProductsViewModel> products) {
        StringBuilder result = new StringBuilder();

        products.forEach(product -> {
            result.append(String.format(
                    "<li><a href=\"/products/details?name=%s\">%s</a></li>",
                    product.getName(),
                    product.getName()
            ));
        });

        return result.toString();
    }
}
