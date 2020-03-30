package com.iteco.photoaggregator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="photos_meta_data", schema="public", catalog="PhotoMetaData")
public class PhotoMetaDataEntity {

    private UUID id;
    private UUID photographerId;
    private String title;
    private Double latitude;
    private Double longitude;
    private Timestamp createDate;

    public PhotoMetaDataEntity(
            UUID photographerId,
            String title,
            Double latitude,
            Double longitude,
            Timestamp createDate
    ) {
        this.photographerId = photographerId;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createDate = createDate;
    }

    public PhotoMetaDataEntity() {}

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public UUID getId() { return this.id; }

    public void setId(UUID id) { this.id = id; }

    @Column(name="photographer")
    public UUID getPhotographerId() { return this.photographerId; }

    public void setPhotographerId(UUID photographerId) { this.photographerId = photographerId; }

    @Column(name="title")
    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    @Column(name="latitude")
    public Double getLatitude() { return this.latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    @Column(name="longitude")
    public Double getLongitude() { return this.longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    @Column(name="create_date")
    public Timestamp getCreateDate() { return this.createDate; }

    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotoMetaDataEntity entity = (PhotoMetaDataEntity)o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(this.id); }

    @Override
    public String toString() {
        return String.format("%s; %s; (%.5f, %.5f); %s", id, title, latitude, longitude, createDate);
    }
}
