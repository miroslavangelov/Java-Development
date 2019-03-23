package app.web.controllers;

import app.domain.models.view.DocumentHomeViewModel;
import app.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            modelAndView.setViewName("redirect:/home");

            return modelAndView;
        }

        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/login");

            return modelAndView;
        }

        List<DocumentHomeViewModel> documents =  this.documentService.findAllDocuments().stream()
                .map(document -> {
                    DocumentHomeViewModel model = this.modelMapper.map(document, DocumentHomeViewModel.class);
                    if (model.getTitle().length() >= 12) {
                        model.setTitle(model.getTitle().substring(0, 12) + "...");
                    }

                    return model;
                })
                .collect(Collectors.toList());

        modelAndView.setViewName("home");
        modelAndView.addObject("documents", documents);

        return modelAndView;
    }
}
