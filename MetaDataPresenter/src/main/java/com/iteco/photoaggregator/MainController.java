package com.iteco.photoaggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {

    private final PhotographerRepository photographerRepository;
    private final PhotoMetaDataRepository photoMetaDataRepository;

    @Autowired
    public MainController(PhotographerRepository photographerRepository, PhotoMetaDataRepository photoMetaDataRepository) {
        this.photographerRepository = photographerRepository;
        this.photoMetaDataRepository = photoMetaDataRepository;
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    @ResponseBody
    public String getPhotographerList() {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<!DOCTYPE html>\n");
        responseBuilder.append("<html>\n");
        responseBuilder.append("<head>\n");
        responseBuilder.append("</head>\n");
        responseBuilder.append("<body>\n");
        List<PhotographerEntity> photographers = photographerRepository.findAll();
        for(PhotographerEntity photographer : photographers) {
            responseBuilder.append(
                    "<a href=\"http://localhost:8080/photos/" + photographer.getId() + "\">");
            responseBuilder.append(photographer.getName());
            responseBuilder.append("</a>\n");
            responseBuilder.append("<br>\n");
        }
        responseBuilder.append("</body>\n");
        responseBuilder.append("</html>");
        return responseBuilder.toString();
    }

    @RequestMapping(path="photos/{photographerId}", method=RequestMethod.GET)
    @ResponseBody
    public String getPhotoList(@PathVariable("photographerId") UUID photographerId) {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<!DOCTYPE html>\n");
        responseBuilder.append("<html>\n");
        responseBuilder.append("<head>\n");
        responseBuilder.append("</head>\n");
        responseBuilder.append("<body>\n");
        Collection<PhotoMetaDataEntity> photos =
                photoMetaDataRepository.findByPhotographerId(photographerId);
        for(PhotoMetaDataEntity photo : photos) {
            responseBuilder.append(photo.toString());
            responseBuilder.append("<br>\n");
        }
        responseBuilder.append("</body>\n");
        responseBuilder.append("</html>");
        return responseBuilder.toString();
    }
}
