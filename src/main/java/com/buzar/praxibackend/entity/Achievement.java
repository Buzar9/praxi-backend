package com.buzar.praxibackend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private int achievementId;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private int level;

    @OneToMany(mappedBy = "achievementId",
            cascade = CascadeType.ALL)
    private List<Quest> questList;

    @ManyToMany(mappedBy = "openAchList")
    private List<User> openUserList;

    @ManyToMany(mappedBy = "finishedAchList")
    private List<User> finishedUserList;

    public Achievement() {
    }

    public Achievement(String title) {
        this.title = title;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Quest> getQuestList() {
        return questList;
    }

    public void setQuestList(List<Quest> questList) {
        this.questList = questList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addQuest(Quest theQuest) {

        if(questList == null) questList = new ArrayList<>();
        questList.add(theQuest);
        theQuest.setAchievementId(this);
    }

    public List<User> getOpenUserList() {
        return openUserList;
    }

    public void setOpenUserList(List<User> userList) {
        this.openUserList = userList;
    }
}