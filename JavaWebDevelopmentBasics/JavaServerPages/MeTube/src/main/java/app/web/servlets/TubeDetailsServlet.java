package app.web.servlets;

import app.domain.models.CreateTubeModel;
import app.domain.models.TubeServiceModel;
import app.services.TubeService;
import app.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeTitle = URLDecoder.decode(req.getQueryString().split("=")[1], "UTF-8");
        TubeServiceModel tube = this.tubeService.findTubeByName(tubeTitle);
        CreateTubeModel createTubeModel = this.modelMapper.map(tube, CreateTubeModel.class);
        req.setAttribute("tube", createTubeModel);
        req.getRequestDispatcher("/views/tube-details.jsp").forward(req, resp);
    }
}
