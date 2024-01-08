package at.videc.survia.node.domain.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> {

    @Id
    @GeneratedValue
    T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getExternalId() { return id.toString(); }
}
