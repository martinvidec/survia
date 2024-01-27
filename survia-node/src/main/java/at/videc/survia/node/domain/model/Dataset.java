package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.IDataset;
import at.videc.survia.node.domain.model.base.BaseEntity;
import at.videc.survia.node.domain.serializer.Base64Deserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

import java.util.Arrays;


@Entity
public class Dataset extends BaseEntity<Long> implements IDataset {

    @Column(name="NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "ORGANIZATION", nullable = false)
    private String organization;
    @Lob
    @Column(name = "LOGO", nullable = true)
    @JsonDeserialize(using = Base64Deserializer.class)
    private byte[] logo;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Override
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

}
