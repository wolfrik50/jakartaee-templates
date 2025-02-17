package io.wulfcodes.plain.datastore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import lombok.extern.log4j.Log4j2;
import io.wulfcodes.plain.factory.MySQLConnectionProviderFactory;
import io.wulfcodes.plain.model.data.CustomerData;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@ApplicationScoped
@Log4j2
public class CustomerDatastore {

    @Inject
    private MysqlConnectionPoolDataSource dataSource;

    public void createCustomer(CustomerData customerData) {
        String query = "SELECT * FROM customers";


        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String postalCode = resultSet.getString("postal_code");
                String createdAt = resultSet.getString("created_at");
                String updatedAt = resultSet.getString("updated_at");

                // Print customer data
                System.out.println("ID: " + id + ", Name: " + name + ", Type: " + type +
                    ", Email: " + email + ", Address: " + address + ", City: " + city +
                    ", State: " + state + ", Postal Code: " + postalCode +
                    ", Created At: " + createdAt + ", Updated At: " + updatedAt);
            }

            resultSet.close();
        } catch (SQLException ex) {
            log.error("Exception occurred while retrieving customers", ex);
        }

    }

}
