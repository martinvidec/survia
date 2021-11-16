package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.ICountry;
import at.videc.survia.node.domain.model.base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Country extends BaseEntity<Long> implements ICountry {

    private String code;
    private String name;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
