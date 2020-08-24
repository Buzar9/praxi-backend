package com.buzar.praxibackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "realization")
public class Realization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "realization_id")
    private int realizationId;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @ManyToMany(mappedBy = "realizationsList",
            cascade = CascadeType.ALL)
    private List<Quest> questsList;

    @Column(name = "quest_id")
    private int questId;

    public Realization() {
    }

    public Realization(String description) {
        this.description = description;

    }

    public int getRealizationId() {
        return realizationId;
    }

    public void setRealizationId(int realizationId) {
        this.realizationId = realizationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(User user) {
        if (username != null) {
            this.username = null;
        }
        this.username = user.getUsername();
    }

    public List<Quest> getQuestsList() {
        return questsList;
    }

    public void setQuestsList(List<Quest> quests) {
        this.questsList = quests;
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(Quest quest) {
        this.questId = quest.getQuestId();
    }
}
