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

}
