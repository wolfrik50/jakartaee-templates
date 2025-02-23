package io.wulfcodes.plain.datastore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import lombok.extern.log4j.Log4j2;
import io.wulfcodes.plain.model.data.InvoiceData;
import io.wulfcodes.plain.model.value.Status;

import static java.util.Locale.ROOT;

@ApplicationScoped
@Log4j2
public class InvoiceDatastore {
    private static final String GET_ALL_INVOICES_BY_CUSTOMER_ID = "SELECT * FROM invoices WHERE customer_id = ?";

    @Inject
    private MysqlConnectionPoolDataSource dataSource;

    public List<InvoiceData> getInvoicesByCustomerId(long customerId) {

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_INVOICES_BY_CUSTOMER_ID);
        ) {
            statement.setLong(1, customerId);

            List<InvoiceData> invoices = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Integer invoiceId = resultSet.getInt("id");
                    Double amount = resultSet.getDouble("amount");
                    String status = resultSet.getString("status");

                    InvoiceData invoice = InvoiceData.builder()
                        .invoiceId(invoiceId)
                        .customerId(Long.valueOf(customerId))
                        .amount(amount)
                        .status(Status.valueOf(status.toUpperCase(ROOT)))
                        .build();

                    invoices.add(invoice);
                }

            } catch (SQLException ex) {
                throw ex;
            }

            return invoices;
        } catch (SQLException ex) {
            log.error("Exception occurred while retrieving customers", ex);
            return Collections.emptyList();
        }
    }

}
