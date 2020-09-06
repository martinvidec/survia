package at.videc.survia.core.domain.model;

import java.time.Instant;

/**
 *
 * @param <T> Base datatype for observations. E.g: Long, BigDecimal.
 */
public interface IObservation<T> {
    Instant getCreated();
    T getValue();
}
