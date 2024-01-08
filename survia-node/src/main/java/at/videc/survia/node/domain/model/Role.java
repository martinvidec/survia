package at.videc.survia.node.domain.model;

import at.videc.survia.node.domain.model.base.BaseEntity;
import at.videc.survia.node.domain.model.constants.RoleName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
public class Role extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
