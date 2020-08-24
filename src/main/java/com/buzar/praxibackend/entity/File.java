package com.buzar.praxibackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private int fileId;

    @Column(name = "file_name")
    private String fileName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "realization_id")
    private Realization realization;

    public File() {
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Realization getRealization() {
        return realization;
    }

    public void setRealization(Realization theRealization) {
        if(realization != null) {
            realization = null;
        }
        this.realization = theRealization;
    }
}

