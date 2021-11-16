package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.IObservation;
import at.videc.survia.node.domain.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Observation extends BaseEntity<Long> implements IObservation {

    @Column(name = "CREATED", nullable = false)
    private Instant created;
    @Column(name = "VALUE", nullable = false, precision = 38, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "DATASET_ID", nullable = false)
    private Dataset dataset;
    @ManyToOne
    @JoinColumn(name = "INDICATOR_ID", nullable = false)
    private Indicator indicator;
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

    @Override
    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
