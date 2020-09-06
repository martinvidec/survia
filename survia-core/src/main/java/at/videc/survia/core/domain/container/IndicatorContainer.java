package at.videc.survia.core.domain.container;

import at.videc.survia.core.domain.model.ICodedValue;
import at.videc.survia.core.domain.model.IDataset;
import at.videc.survia.core.domain.model.IIndicator;
import at.videc.survia.core.domain.model.IMeasurementUnit;

import java.util.List;

public class IndicatorContainer<T> {

    private IIndicator<T> indicator;
    private IDataset dataset;
    private IMeasurementUnit measurementUnit;
    private List<ICodedValue<T>> codedValueList;

    public IIndicator<T> getIndicator() {
        return indicator;
    }

    public void setIndicator(IIndicator<T> indicator) {
        this.indicator = indicator;
    }

    public IDataset getDataset() {
        return dataset;
    }

    public void setDataset(IDataset dataset) {
        this.dataset = dataset;
    }

    public IMeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(IMeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public List<ICodedValue<T>> getCodedValueList() {
        return codedValueList;
    }

    public void setCodedValueList(List<ICodedValue<T>> codedValueList) {
        this.codedValueList = codedValueList;
    }
}
