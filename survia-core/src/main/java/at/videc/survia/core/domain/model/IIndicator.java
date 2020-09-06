package at.videc.survia.core.domain.model;

/**
 *
 * @param <T> Base datatype for observations. E.g: Long, BigDecimal.
 */
public interface IIndicator<T> {
    String getName();
    T getMin();
    T getMax();
    boolean isCoded();
    boolean isHigherBetter();
}
