package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.IMeasurementUnit;
import at.videc.survia.node.domain.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class MeasurementUnit extends BaseEntity<Long> implements IMeasurementUnit {

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

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
}
