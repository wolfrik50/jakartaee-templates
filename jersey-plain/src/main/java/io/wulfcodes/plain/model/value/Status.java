package io.wulfcodes.plain.model.value;

public enum Status {
    BILLED, PAID, VOID;

    public String getStatus() {
        return this.name().toLowerCase();
    }
}
