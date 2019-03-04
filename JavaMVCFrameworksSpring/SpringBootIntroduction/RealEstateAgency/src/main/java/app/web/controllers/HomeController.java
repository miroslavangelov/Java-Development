package app.web.controllers;

import app.domain.models.view.OfferViewModel;
import app.service.OfferService;
import app.util.HtmlReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private static final String INDEX_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\index.html";

    private final OfferService offerService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        List<OfferViewModel> offers = this.offerService.findAll().stream()
                .map(offer -> this.modelMapper.map(offer, OfferViewModel.class))
                .collect(Collectors.toList());

        StringBuilder htmlContent = new StringBuilder();

        if (offers.size() > 0) {
            for (OfferViewModel offer: offers) {
                htmlContent.append("<div class=\"apartment\">")
                        .append(String.format("<p>Rent: %.2f</p>", offer.getApartmentRent()))
                        .append(String.format("<p>Type: %s</p>", offer.getApartmentType()))
                        .append(String.format("<p>Commission: %.2f</p>", offer.getAgencyCommission()))
                        .append("</div>");
            }
        } else {
            htmlContent.append("<div class=\"apartment\" style=\"border: red solid 1px\">")
                    .append("There aren't any offers!")
                    .append("</div>");
        }

        return this.htmlReader.readHtml(INDEX_PATH)
                .replace("{{offers}}", htmlContent.toString().trim());
    }
}
