package io.wulfcodes.plain.model.data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.wulfcodes.plain.model.value.Status;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InvoiceData {

    private Long invoiceId;
    private Long customerId;
    private Double amount;
    private Status status;
    private LocalDateTime billedDated;
    private LocalDateTime paidDated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
