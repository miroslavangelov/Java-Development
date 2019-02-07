package app.web.servlets;

import app.domain.models.AllTubesModel;
import app.services.TubeService;
import app.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public AllTubesServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AllTubesModel> tubes = this.tubeService.findAllTubes().stream()
                .map(tube -> this.modelMapper.map(tube, AllTubesModel.class))
                .collect(Collectors.toList());
        req.setAttribute("tubes", tubes);
        req.getRequestDispatcher("/views/all-tubes.jsp").forward(req, resp);
    }
}
