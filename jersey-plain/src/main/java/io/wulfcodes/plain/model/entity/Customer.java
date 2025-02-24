package io.wulfcodes.plain.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.wulfcodes.plain.model.value.Type;

@Data @NoArgsConstructor @AllArgsConstructor
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = -8579470110657194439L;

    private Long id;
    private String name;
    private Type type;
    private String email;
    private String address;
    private String plot;
    private String city;
    private String state;
    private String postalCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
