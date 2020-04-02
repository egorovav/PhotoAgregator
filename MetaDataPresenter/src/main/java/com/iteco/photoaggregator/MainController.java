package com.iteco.photoaggregator;

import com.iteco.photoaggregator.model.PhotoMetadataEntity;
import com.iteco.photoaggregator.model.PhotoMetaDataRepository;
import com.iteco.photoaggregator.model.PhotographerEntity;
import com.iteco.photoaggregator.model.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final PhotoMetaDataRepository photoMetadataRepository;
    private final int pageSize = 20;

    @Autowired
    public MainController(PhotographerRepository photographerRepository, PhotoMetaDataRepository photoMetaDataRepository) {
        this.photographerRepository = photographerRepository;
        this.photoMetadataRepository = photoMetaDataRepository;
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
        Collection<PhotoMetadataEntity> photos =
                photoMetadataRepository.findByPhotographerId(photographerId);
        uiModel.addAttribute("photos", photos);

        Optional<PhotographerEntity> photographer = photographerRepository.findById(photographerId);
        if(!photographer.isPresent()) {
            return "error";
        }
        uiModel.addAttribute("photographer", photographer.get());

        return "photos";
    }

    @GetMapping(path="photos/{photographerId}/{sortColumn}/{pageNumber}")
    public String photoPage(@PathVariable UUID photographerId,
                            @PathVariable String sortColumn,
                            @PathVariable int pageNumber,
                            Model uiModel) {

        uiModel.addAttribute("sortColumn", sortColumn);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortColumn));
        Page<PhotoMetadataEntity> photoPage =
                photoMetadataRepository.findByPhotographerId(photographerId, pageable);
        uiModel.addAttribute("page", photoPage);

        Optional<PhotographerEntity> photographer = photographerRepository.findById(photographerId);
        if(!photographer.isPresent()) {
            return "error";
        }
        uiModel.addAttribute("photographer", photographer.get());

        int totalPages = photoPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            uiModel.addAttribute("pageNumbers", pageNumbers);
        }

        return "paged/photos";
    }
}
