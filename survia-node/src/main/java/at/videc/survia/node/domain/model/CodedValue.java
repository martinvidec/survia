package at.videc.survia.node.domain.model;

import at.videc.survia.core.domain.model.ICodedValue;
import at.videc.survia.node.domain.model.base.BaseEntity;
import jakarta.persistence.Entity;


import java.math.BigDecimal;

@Entity
public class CodedValue extends BaseEntity<Long> implements ICodedValue<BigDecimal> {

    private BigDecimal Code;
    private String Value;

    @Override
    public BigDecimal getCode() {
        return Code;
    }

    public void setCode(BigDecimal code) {
        Code = code;
    }

    @Override
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
