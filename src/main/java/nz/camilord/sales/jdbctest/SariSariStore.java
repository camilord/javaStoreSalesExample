package nz.camilord.sales.jdbctest;

import nz.camilord.sales.jdbctest.Common.DBConfig;
import nz.camilord.sales.jdbctest.Common.DatabaseConnectionManager;
import nz.camilord.sales.jdbctest.Model.Order;
import nz.camilord.sales.jdbctest.Service.OrdersDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class SariSariStore
{
    public static void main(String... args) {
        System.out.println("\n");

        DBConfig config = new DBConfig();

        DatabaseConnectionManager queryDriver = new DatabaseConnectionManager(
                config.getConfig().getProperty("host"),
                config.getConfig().getProperty("dbname"),
                config.getConfig().getProperty("username"),
                config.getConfig().getProperty("password")
        );


        try {
            Connection connection = queryDriver.getConnection();

            OrdersDAO ordersService = new OrdersDAO(connection);
            List<Order> orders = ordersService.findAllByCustomer(2);

            for (Order order : orders) {
                System.out.println(order);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
