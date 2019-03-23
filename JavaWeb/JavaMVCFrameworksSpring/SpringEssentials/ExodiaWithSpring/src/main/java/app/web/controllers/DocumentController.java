package app.web.controllers;

import app.domain.models.binding.DocumentScheduleBindingModel;
import app.domain.models.service.DocumentServiceModel;
import app.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DocumentController {
    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/schedule")
    public ModelAndView scheduleView(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/login");

            return modelAndView;
        }

        modelAndView.setViewName("schedule");

        return modelAndView;
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleAction(@ModelAttribute DocumentScheduleBindingModel documentScheduleBindingModel, ModelAndView modelAndView) {
        DocumentServiceModel documentServiceModel = this.documentService.scheduleDocument(this.modelMapper.map(documentScheduleBindingModel, DocumentServiceModel.class));
        modelAndView.setViewName("redirect:/details/" + documentServiceModel.getId());

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable(name = "id") String documentId, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/login");

            return modelAndView;
        }

        DocumentServiceModel documentServiceModel = this.documentService.findById(documentId);
        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Document not found!");
        }

        modelAndView.setViewName("details");
        modelAndView.addObject("document", documentServiceModel);

        return modelAndView;
    }

    @GetMapping("/print/{id}")
    public ModelAndView printView(@PathVariable(name = "id") String documentId, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/login");

            return modelAndView;
        }

        DocumentServiceModel documentServiceModel = this.documentService.findById(documentId);
        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Document not found!");
        }

        modelAndView.setViewName("print");
        modelAndView.addObject("document", documentServiceModel);

        return modelAndView;
    }

    @PostMapping("/print/{id}")
    public ModelAndView printAction(@PathVariable(name = "id") String documentId, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/login");

            return modelAndView;
        }

        this.documentService.deleteById(documentId);
        modelAndView.setViewName("redirect:/home");

        return modelAndView;
    }
}
