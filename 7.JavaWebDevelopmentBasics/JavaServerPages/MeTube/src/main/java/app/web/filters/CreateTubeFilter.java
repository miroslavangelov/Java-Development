package app.web.filters;

import app.domain.models.CreateTubeModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class CreateTubeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (httpServletRequest.getMethod().toLowerCase().equals("post")) {
            CreateTubeModel createTubeModel = new CreateTubeModel();
            createTubeModel.setTitle(httpServletRequest.getParameter("title"));
            createTubeModel.setDescription(httpServletRequest.getParameter("description"));
            createTubeModel.setYouTubeLink(httpServletRequest.getParameter("youTubeLink"));
            createTubeModel.setUploader(httpServletRequest.getParameter("uploader"));

            httpServletRequest.setAttribute("createTubeModel", createTubeModel);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
