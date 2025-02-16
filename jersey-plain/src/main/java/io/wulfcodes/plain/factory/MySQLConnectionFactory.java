package io.wulfcodes.plain.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.apache.commons.configuration2.Configuration;

@Dependent
public class MySQLConnectionFactory implements Factory<Connection> {

    private Configuration config;

    private MysqlConnectionPoolDataSource dataSource;

    @Inject
    public MySQLConnectionFactory(Configuration config) {
        this.config = config;
    }

    @PostConstruct
    private void init() {
        dataSource = new MysqlConnectionPoolDataSource();

        dataSource.setURL(config.getString("db.mysql.url"));
        dataSource.setUser(config.getString("db.mysql.username"));
        dataSource.setPassword(config.getString("db.mysql.password"));
        dataSource.setServerName(config.getString("db.mysql.server", "localhost"));
        dataSource.setPort(config.getInt("db.mysql.port", 3306));
        dataSource.setDatabaseName(config.getString("db.mysql.database", "plain_db"));
    }

    @Produces
    @Named("mysql-connection")
    public Connection provide() throws SQLException {
        return dataSource.getConnection();
    }


    public void dispose(@Disposes Connection conn) {
        try {
            if (Objects.nonNull(conn) && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
