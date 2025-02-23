package io.wulfcodes.plain.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.wulfcodes.plain.model.value.Status;

@Data @NoArgsConstructor @AllArgsConstructor
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 847646683776025001L;

    private Integer id;
    private Long customerId;
    private Double amount;
    private Status status;
    private LocalDateTime billedDated;
    private LocalDateTime paidDated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
