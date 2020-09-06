package at.videc.survia.core.domain.container;

import at.videc.survia.core.domain.model.ICountry;
import at.videc.survia.core.domain.model.IDataset;
import at.videc.survia.core.domain.model.IIndicator;
import at.videc.survia.core.domain.model.IObservation;

public class ObservationContainer<T> {
    private IObservation<T> observation;
    private IDataset dataset;
    private IIndicator<T> indicator;
    private ICountry country;
}
