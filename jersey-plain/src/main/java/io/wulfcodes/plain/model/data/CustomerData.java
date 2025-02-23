package io.wulfcodes.plain.model.data;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.wulfcodes.plain.model.value.Type;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerData {

    private Long customerId;
    private String name;
    private Type type;
    private String email;
    private String address;
    private String plot;
    private String city;
    private String state;
    private String postalCode;
    //    private LocalDateTime createdAt;
    //    private LocalDateTime updatedAt;
    private List<InvoiceData> invoices;

}
