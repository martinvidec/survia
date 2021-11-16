package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.IDataset;
import at.videc.survia.node.domain.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Dataset extends BaseEntity<Long> implements IDataset {

    @Column(name="NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "ORGANIZATION", nullable = false)
    private String organization;
    @Column(name = "LOGO", nullable = true)
    private Byte[] logo;

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
    public Byte[] getLogo() {
        return logo;
    }

    public void setLogo(Byte[] logo) {
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
