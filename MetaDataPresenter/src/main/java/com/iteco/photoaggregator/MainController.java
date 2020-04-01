package com.iteco.photoaggregator;

import com.iteco.photoaggregator.model.PhotoMetaDataEntity;
import com.iteco.photoaggregator.model.PhotoMetaDataRepository;
import com.iteco.photoaggregator.model.PhotographerEntity;
import com.iteco.photoaggregator.model.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private final PhotographerRepository photographerRepository;
    private final PhotoMetaDataRepository photoMetaDataRepository;
    private final int pageSize = 20;

    @Autowired
    public MainController(PhotographerRepository photographerRepository, PhotoMetaDataRepository photoMetaDataRepository) {
        this.photographerRepository = photographerRepository;
        this.photoMetaDataRepository = photoMetaDataRepository;
    }

    @GetMapping(path="/")
    public String photographerList(Model uiModel) {
        List<PhotographerEntity> photographers = photographerRepository.findAll();
        uiModel.addAttribute("photographers", photographers);
        return "photographers";
    }

    @GetMapping(path="/{pageNumber}")
    public String photographerPage(@PathVariable int pageNumber, Model uiModel) {
        Page<PhotographerEntity> photographerPage =
                photographerRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        uiModel.addAttribute("page", photographerPage);

        int totalPages = photographerPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            uiModel.addAttribute("pageNumbers", pageNumbers);
        }

        return "paged/photographers";
    }

    @GetMapping(path="photos/{photographerId}")
    public String photoList(@PathVariable("photographerId") UUID photographerId, Model uiModel) {
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
