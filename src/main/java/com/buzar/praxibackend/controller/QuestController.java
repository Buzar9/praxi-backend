package com.buzar.praxibackend.controller;

import com.buzar.praxibackend.entity.Quest;
import com.buzar.praxibackend.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quests")
@CrossOrigin(origins = "*")
public class QuestController {

    @Autowired
    private QuestRepository questRepositoryImpl;

    @GetMapping
    public List<Quest> questList(){

        return questRepositoryImpl.findAll();
    }

    @GetMapping("{questId}")
    public Quest findById(@PathVariable int questId) {

        return questRepositoryImpl.findById(questId);
    }

    @PostMapping("/achievement/{achievementId}")
    public void saveQuest(@PathVariable int achievementId,
                          @RequestBody Quest tempQuest){

        tempQuest.setQuestId(0);
        questRepositoryImpl.save(tempQuest, achievementId);
    }

    @PutMapping("{questId}/achievement/{achievementId}")
    public String updateQuest(@PathVariable int questId,
                            @PathVariable int achievementId,
                            @RequestBody Quest tempQuest) {

        tempQuest.setQuestId(questId);
        try {
            return questRepositoryImpl.update(tempQuest, achievementId);
        } catch (ObjectOptimisticLockingFailureException exc) {
            return "Data doesn't exist";
        }
    }

    @DeleteMapping("{questId}")
    public void deleteQuest(@PathVariable int questId) {

        questRepositoryImpl.delete(questId);
    }
}
