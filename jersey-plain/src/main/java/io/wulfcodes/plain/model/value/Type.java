package io.wulfcodes.plain.model.value;

public enum Type {
    INDIVIDUAL, BUSINESS;

    public String getType() {
        return this.name().toLowerCase();
    }
}
