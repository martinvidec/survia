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

    public IObservation<T> getObservation() {
        return observation;
    }

    public void setObservation(IObservation<T> observation) {
        this.observation = observation;
    }

    public IDataset getDataset() {
        return dataset;
    }

    public void setDataset(IDataset dataset) {
        this.dataset = dataset;
    }

    public IIndicator<T> getIndicator() {
        return indicator;
    }

    public void setIndicator(IIndicator<T> indicator) {
        this.indicator = indicator;
    }

    public ICountry getCountry() {
        return country;
    }

    public void setCountry(ICountry country) {
        this.country = country;
    }
}
