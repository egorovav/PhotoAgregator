package com.iteco.photoaggregator;

import com.iteco.photoaggregator.model.PhotoMetaDataEntity;
import com.iteco.photoaggregator.model.PhotoMetaDataRepository;
import com.iteco.photoaggregator.model.PhotographerEntity;
import com.iteco.photoaggregator.model.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    private final PhotographerRepository photographerRepository;
    private final PhotoMetaDataRepository photoMetaDataRepository;

    @Autowired
    public MainController(PhotographerRepository photographerRepository, PhotoMetaDataRepository photoMetaDataRepository) {
        this.photographerRepository = photographerRepository;
        this.photoMetaDataRepository = photoMetaDataRepository;
    }

//    @GetMapping(path="/")
//    public String getPhotographerList() {
//        StringBuilder responseBuilder = new StringBuilder();
//        responseBuilder.append("<!DOCTYPE html>\n");
//        responseBuilder.append("<html>\n");
//        responseBuilder.append("<head>\n");
//        responseBuilder.append("</head>\n");
//        responseBuilder.append("<body>\n");
//        List<PhotographerEntity> photographers = photographerRepository.findAll();
//        for(PhotographerEntity photographer : photographers) {
//            responseBuilder.append("<a href=\"photos/" + photographer.getId() + "\">");
//            responseBuilder.append(photographer.getName());
//            responseBuilder.append("</a>\n");
//            responseBuilder.append("<br>\n");
//        }
//        responseBuilder.append("</body>\n");
//        responseBuilder.append("</html>");
//        return responseBuilder.toString();
//    }

    @GetMapping(path="/")
    public String photographerList(Model uiModel) {
        List<PhotographerEntity> photographers = photographerRepository.findAll();
        uiModel.addAttribute("photographers", photographers);
        return "photographers";
    }

    @GetMapping(path="photos/{photographerId}")
    public String getPhotoList(@PathVariable("photographerId") UUID photographerId, Model uiModel) {
        Collection<PhotoMetaDataEntity> photos =
                photoMetaDataRepository.findByPhotographerId(photographerId);
        uiModel.addAttribute("photos", photos);

        Optional<PhotographerEntity> photographer = photographerRepository.findById(photographerId);
        if(!photographer.isPresent()) {
            return "error";
        }
        uiModel.addAttribute("photographer", photographer.get());

        return "photos";
    }
}
