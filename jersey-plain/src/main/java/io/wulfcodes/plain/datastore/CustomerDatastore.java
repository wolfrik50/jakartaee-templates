package io.wulfcodes.plain.datastore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import lombok.extern.log4j.Log4j2;
import io.wulfcodes.plain.model.data.CustomerData;
import io.wulfcodes.plain.model.value.Type;

import static java.util.Locale.ROOT;
import static java.sql.Statement.RETURN_GENERATED_KEYS;


@ApplicationScoped
@Log4j2
public class CustomerDatastore {
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM customers";

    @Inject
    private MysqlConnectionPoolDataSource dataSource;

    public List<CustomerData> getCustomers() {
        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_CUSTOMERS);
        ) {
            List<CustomerData> customers = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String postalCode = resultSet.getString("postal_code");

                customers.add(CustomerData.builder()
                    .customerId(id)
                    .name(name)
                    .type(Type.valueOf(type.toUpperCase(ROOT)))
                    .email(email)
                    .address(address)
                    .postalCode(postalCode)
                    .build());
            }

            return customers;

        } catch (SQLException ex) {
            log.error("Exception occurred while retrieving customers", ex);
            return Collections.emptyList();
        }

    }

}
