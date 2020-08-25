package com.buzar.praxibackend.controller;

import com.buzar.praxibackend.entity.Achievement;
import com.buzar.praxibackend.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
@CrossOrigin(origins = "*")
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepositoryImpl;

    @GetMapping
    public List<Achievement> getAllAchievements() {

        return achievementRepositoryImpl.findAll();
    }

    @GetMapping("{achievementId}")
    public Achievement getSingleAchievement(@PathVariable int achievementId) {

        return achievementRepositoryImpl.findById(achievementId);
    }

    @PostMapping
    public void saveAchievement(@RequestBody Achievement tempAchievement) {

        tempAchievement.setAchievementId(0);
        achievementRepositoryImpl.saveOrUpdate(tempAchievement);
    }

    @PutMapping("{achievementId}")
    public void updateAchievements(@PathVariable int achievementId,
                                   @RequestBody Achievement tempAchievement) {

        tempAchievement.setAchievementId(achievementId);
        achievementRepositoryImpl.saveOrUpdate(tempAchievement);
    }

    @PutMapping("{achievementId}/add/user/{userId}")
    public String addAchievementToUser(@PathVariable int achievementId,
                                     @PathVariable int userId){
        return achievementRepositoryImpl.addAchievementToUser(achievementId, userId);
    }

    @PutMapping("{achievementId}/remove/user/{userId}")
    public void removeAchievementFromUser(@PathVariable int achievementId,
                                          @PathVariable int userId) {
        achievementRepositoryImpl.removeAchievementFromUser(achievementId, userId);
    }

    @PutMapping("{achievementId}/finish/user/{userId}")
    public void finishAchievementForUser(@PathVariable int achievementId,
                                         @PathVariable int userId) {
        achievementRepositoryImpl.finishAchievementForUser(achievementId, userId);
    }

    @PutMapping("{achievementId}/take/user/{userId}")
    public void takeAchievementForUser(@PathVariable int achievementId,
                                       @PathVariable int userId) {
        achievementRepositoryImpl.takeAchievementFromUser(achievementId, userId);
    }

    @DeleteMapping("{achievementId}")
    public void deleteAchievement(@PathVariable int achievementId) {

        achievementRepositoryImpl.delete(achievementId);
    }
}
