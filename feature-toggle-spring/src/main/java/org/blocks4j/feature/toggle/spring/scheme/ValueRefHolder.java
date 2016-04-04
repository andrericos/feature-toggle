package org.blocks4j.feature.toggle.spring.scheme;


public class ValueRefHolder {
    private String held;
    private HoldingType type;

    public ValueRefHolder(HoldingType type, String held) {
        this.type = type;
        this.held = held;
    }

    public boolean hasRef() {
        return this.type == HoldingType.REF;
    }

    public boolean hasValue() {
        return this.type == HoldingType.VALUE;
    }

    public String getValue() {
        return (this.type == HoldingType.REF) ? null : this.held;
    }

    public String getRef() {
        return (this.type == HoldingType.REF) ? this.held : null;
    }

    enum HoldingType {
        VALUE, REF
    }

    @Override
    public String toString() {
        return String.format("[hasValue: '%s', hasRef: '%s', value: '%s', ref: '%s']", this.hasValue(), this.hasRef(), this.getValue(), this.getRef());
    }
}