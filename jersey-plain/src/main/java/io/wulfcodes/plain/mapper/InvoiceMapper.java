package io.wulfcodes.plain.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import io.wulfcodes.plain.model.data.InvoiceData;
import io.wulfcodes.plain.model.entity.Invoice;

@ApplicationScoped
@Mapper(componentModel = "cdi")
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "id", target = "invoiceId")
    InvoiceData toData(Invoice invoice);

    @Mapping(source = "invoiceId", target = "id")
    Invoice toEntity(InvoiceData invoiceData);
}

