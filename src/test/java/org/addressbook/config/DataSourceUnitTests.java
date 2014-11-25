package org.addressbook.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author alisiikh.
 */
@ContextConfiguration(classes = {DataSourceConfig.class})
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceUnitTests {

    @Inject
    private DataSource dataSource;

    @Test
    public void testConnectionIsAcquired() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();

        assert stmt.execute("select 1");
    }
}
