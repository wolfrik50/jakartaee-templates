package io.wulfcodes.plain.datastore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    private static final String GET_ALL_INVOICES = "SELECT * FROM invoices";
    private static final String GET_ALL_INVOICES_BY_CUSTOMER_ID = "SELECT * FROM invoices WHERE customer_id = ?";

    @Inject
    private MysqlConnectionPoolDataSource dataSource;

    public List<InvoiceData> getInvoices() {

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_INVOICES);
        ) {

            List<InvoiceData> invoices = new ArrayList<>();

            while (resultSet.next()) {
                Long invoiceId = resultSet.getLong("id");
                Long customerId = resultSet.getLong("customer_id");
                Double amount = resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                Timestamp billedDated = resultSet.getTimestamp("billed_dated");
                Timestamp paidDated = resultSet.getTimestamp("paid_dated");

                InvoiceData invoice = InvoiceData.builder()
                    .invoiceId(invoiceId)
                    .customerId(customerId)
                    .amount(amount)
                    .status(Status.valueOf(status.toUpperCase(ROOT)))
                    .billedDated(billedDated.toLocalDateTime())
                    .paidDated(Optional.ofNullable(paidDated).map(Timestamp::toLocalDateTime).orElse(null))
                    .build();

                invoices.add(invoice);
            }

            return invoices;
        } catch (SQLException ex) {
            log.error("Exception occurred while retrieving customers", ex);
            return Collections.emptyList();
        }
    }

    public List<InvoiceData> getInvoicesByCustomerId(long customerId) {

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_INVOICES_BY_CUSTOMER_ID);
        ) {
            statement.setLong(1, customerId);

            List<InvoiceData> invoices = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Long invoiceId = resultSet.getLong("id");
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
