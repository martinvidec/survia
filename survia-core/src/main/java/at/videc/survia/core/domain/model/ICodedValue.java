package at.videc.survia.core.domain.model;

public interface ICodedValue<T> {
    T getCode();
    String getValue();
}
