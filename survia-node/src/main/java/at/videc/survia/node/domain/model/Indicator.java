package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.ICodedValue;
import at.videc.survia.core.domain.model.IIndicator;
import at.videc.survia.node.domain.model.base.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Indicator extends BaseEntity<Long> implements IIndicator<BigDecimal> {

    @Column(name="name", nullable = false)
    private String name;
    @Column(name = "MIN", nullable = false, precision = 38, scale = 2)
    private BigDecimal min;
    @Column(name = "MAX", nullable = false, precision = 38, scale = 2)
    private BigDecimal max;
    @Column(name="CODED", nullable = false)
    private boolean coded;
    @Column(name = "HIGHER_BETTER", nullable = false)
    private boolean higherBetter;

    @ManyToOne
    @JoinColumn(name = "DATASET_ID", nullable = false)
    private Dataset dataset;

    @ManyToOne
    @JoinColumn(name = "MEASUREMENT_ID", nullable = false)
    private MeasurementUnit measurementUnit;

    @OneToMany
    private List<CodedValue> codedValues;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    @Override
    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @Override
    public boolean isCoded() {
        return coded;
    }

    public void setCoded(boolean coded) {
        this.coded = coded;
    }

    @Override
    public boolean isHigherBetter() {
        return higherBetter;
    }

    public void setHigherBetter(boolean highBetter) {
        this.higherBetter = highBetter;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public List<CodedValue> getCodedValues() {
        return codedValues;
    }

    public void setCodedValues(List<CodedValue> codedValues) {
        this.codedValues = codedValues;
    }
}
