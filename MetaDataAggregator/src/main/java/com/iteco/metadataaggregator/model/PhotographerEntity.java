package com.iteco.metadataaggregator.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="photographers", schema="public", catalog="PhotoMetaData")
public class PhotographerEntity {

    private UUID id;
    private String name;

    public PhotographerEntity() {}

    public PhotographerEntity(String name) {
        this.name = name;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    @Column(name="name")
    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotographerEntity entity = (PhotographerEntity)o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(this.id); }

    @Override
    public String toString() { return  this.id + "; " + this.name; }
}
