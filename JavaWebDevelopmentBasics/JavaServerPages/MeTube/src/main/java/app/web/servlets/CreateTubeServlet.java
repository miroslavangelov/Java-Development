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

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public CreateTubeServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/create-tube.jsp").forward(req, resp);
        this.tubeService.findAllTubes();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateTubeModel createTubeModel = (CreateTubeModel) req.getAttribute("createTubeModel");

        this.tubeService.saveTube(this.modelMapper.map(createTubeModel, TubeServiceModel.class));

        resp.sendRedirect("/tubes/details?title=" + createTubeModel.getTitle());
    }
}
