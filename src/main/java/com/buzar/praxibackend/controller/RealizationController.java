package com.buzar.praxibackend.controller;

import com.buzar.praxibackend.entity.Realization;
import com.buzar.praxibackend.repository.RealizationRepository;
import com.buzar.praxibackend.service.RealizationFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realizations")
@CrossOrigin(origins = "*")
public class RealizationController {

    @Autowired
    private RealizationRepository realizationRepositoryImpl;

    @Autowired
    private RealizationFileService realizationFileService;

    @GetMapping
    public List<Realization> realizationList(){

        return realizationRepositoryImpl.findAll();
    }

    @GetMapping("/{realizationId}")
    public Realization findSingleRealization(@PathVariable int realizationId) {

        return realizationRepositoryImpl.findById(realizationId);
    }

    @PostMapping("/user/{userId}/quest/{questId}")
    public String saveRealization(@PathVariable int userId,
                                @PathVariable int questId,
                                @RequestBody Realization tempRealization) {

        tempRealization.setRealizationId(0);
        try {
            realizationRepositoryImpl.save(tempRealization, userId, questId);
        } catch (Exception exc) {
            return "Data does't exist";
        }

        realizationRepositoryImpl.addRelationQuestReal(tempRealization.getRealizationId(), questId);
        realizationRepositoryImpl.setRealizationData(tempRealization.getRealizationId());

        return realizationRepositoryImpl.save(tempRealization, userId, questId);
    }



    @PutMapping("/{realizationId}/user/{userId}/quest/{questId}")
    public String updateRealization(@PathVariable int realizationId,
                                  @PathVariable int userId,
                                  @PathVariable int questId,
                                  @RequestBody Realization tempRealization){

        tempRealization.setRealizationId(realizationId);
        try {
            realizationRepositoryImpl.update(tempRealization, userId, questId);
        } catch (Exception exc) {
            return "Data doesn't exist";
        }

        realizationRepositoryImpl.setRealizationData(realizationId);

        return realizationRepositoryImpl.update(tempRealization, userId, questId);
    }

    @DeleteMapping("/{realizationId}")
    public void deleteRealization(@PathVariable int realizationId){

        realizationRepositoryImpl.removeRelationQuestReal(realizationId);
        realizationRepositoryImpl.delete(realizationId);
    }
}
