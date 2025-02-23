package io.wulfcodes.plain.model.exchange;

import java.util.List;
import io.wulfcodes.plain.model.data.CustomerData;

public record CustomerResponse(String status, String message, Object data) {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static CustomerResponse success(String message) {
        return new CustomerResponse(SUCCESS, message, null);
    }

    public static CustomerResponse error(String message) {
        return new CustomerResponse(ERROR, message, null);
    }

    public static CustomerResponse success(String message, List<CustomerData> payload) {
        return new CustomerResponse(SUCCESS, message, payload);
    }
}
