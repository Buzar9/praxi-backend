package com.buzar.praxibackend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "roles")
    private String roles;

    @OneToMany(mappedBy = "userId")
    private List<Realization> realizationList;

    @ManyToMany
    @JoinTable(
            name = "user_achievement_open",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<Achievement> openAchList;

    @ManyToMany
    @JoinTable(
            name = "user_achievement_finished",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<Achievement> finishedAchList;

    public User() {
    }

    public User(String username, String password, boolean enabled, String roles) {

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Realization> getRealizationList() {
        return realizationList;
    }

    public void setRealizationList(List<Realization> realizationList) {
        this.realizationList = realizationList;
    }

    public List<Achievement> getOpenAchList() {
        return openAchList;
    }

    public void setOpenAchList(List<Achievement> achievementList) {
        this.openAchList = achievementList;
    }

    public void addOpenAch(Achievement theAchievement) {

        if (openAchList == null) openAchList = new ArrayList<>();
        openAchList.add(theAchievement);
    }

    public void removeOpenAch(Achievement theAchievement) {

        if (openAchList.contains(theAchievement)) openAchList.remove(theAchievement);
    }

    public List<Achievement> getFinishedAchList() {
        return finishedAchList;
    }

    public void setFinishedAchList(List<Achievement> finishedAchList) {
        this.finishedAchList = finishedAchList;
    }

    public void addFinishedAch(Achievement theAchievement) {

        if (finishedAchList == null) finishedAchList = new ArrayList<>();
        if (!finishedAchList.contains(theAchievement)) finishedAchList.add(theAchievement);
    }

    public void removeFinishedAch(Achievement theAchievement) {

        if (finishedAchList.contains(theAchievement)) finishedAchList.remove(theAchievement);
    }
}