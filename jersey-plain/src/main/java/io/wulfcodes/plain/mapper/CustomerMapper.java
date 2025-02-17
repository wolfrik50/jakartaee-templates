package io.wulfcodes.plain.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import io.wulfcodes.plain.model.data.CustomerData;
import io.wulfcodes.plain.model.entity.Customer;

@ApplicationScoped
@Mapper(componentModel = "cdi")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "id", target = "customerId")
    CustomerData toData(Customer customer);

    @Mapping(source = "customerId", target = "id")
    Customer toEntity(CustomerData customerData);
}

