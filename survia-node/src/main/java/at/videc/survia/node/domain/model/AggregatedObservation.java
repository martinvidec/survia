package at.videc.survia.node.domain.model;

import at.videc.survia.node.domain.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;


import java.math.BigDecimal;

@Entity(name = "AGGREGATED_OBSERVATIONS_V")
public class AggregatedObservation extends BaseEntity<Long> {

    private BigDecimal total;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Indicator indicator;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
