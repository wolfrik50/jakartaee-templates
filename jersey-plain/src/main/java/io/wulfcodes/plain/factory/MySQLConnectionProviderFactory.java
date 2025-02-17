package io.wulfcodes.plain.factory;

import java.sql.SQLException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.apache.commons.configuration2.Configuration;

@Dependent
public class MySQLConnectionProviderFactory implements Factory<MysqlConnectionPoolDataSource> {

    private Configuration config;

    private MysqlConnectionPoolDataSource dataSource;

    @Inject
    public MySQLConnectionProviderFactory(Configuration config) {
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
    @Named("mysql-conn-provider")
    public MysqlConnectionPoolDataSource provide() throws SQLException {
        return dataSource;
    }


    public void dispose(@Disposes MysqlConnectionPoolDataSource datasource) {}
}
