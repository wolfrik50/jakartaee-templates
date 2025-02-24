package io.wulfcodes.plain.model.exchange;

import java.util.List;
import io.wulfcodes.plain.model.data.InvoiceData;

public record InvoiceResponse(String status, String message, Object data) {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static InvoiceResponse success(String message) {
        return new InvoiceResponse(SUCCESS, message, null);
    }

    public static InvoiceResponse error(String message) {
        return new InvoiceResponse(ERROR, message, null);
    }

    public static InvoiceResponse success(String message, List<InvoiceData> payload) {
        return new InvoiceResponse(SUCCESS, message, payload);
    }
}