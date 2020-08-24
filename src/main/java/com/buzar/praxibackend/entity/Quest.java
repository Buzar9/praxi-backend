package com.buzar.praxibackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quest")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_id")
    private int questId;

    @Column(name = "description")
    private String description;

    @Column(name = "job")
    private String job;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievementId;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "quest_realization",
            joinColumns = @JoinColumn(name = "quest_id"),
            inverseJoinColumns = @JoinColumn(name = "realization_id"))
    private List<Realization> realizationsList;

    public Quest() {
    }


    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questsId) {
        this.questId = questsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Achievement getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Achievement achievement) {
        if(achievementId != null) {
            achievementId = null;
        }
        this.achievementId = achievement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(Achievement achievement) {
        if (achievement == null) {
            this.title = null;
        } else {
            this.title = achievement.getTitle();
        }
    }

    public List<Realization> getRealizationsList() {
        return realizationsList;
    }

    public void setRealizationsList(List<Realization> realizations) {
        this.realizationsList = realizations;
    }

    public void addRealization(Realization theRealization) {

        if (realizationsList == null) {
            realizationsList = new ArrayList<>();
        }
        realizationsList.add(theRealization);
    }

    public void removeRealization(Realization theRealization) {
        if(realizationsList.contains(theRealization)) realizationsList.remove(theRealization);
    }
}
