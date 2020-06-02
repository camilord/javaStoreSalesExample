package nz.camilord.sales.jdbctest.Service;

import nz.camilord.sales.jdbctest.Common.BaseDataAccessObject;
import nz.camilord.sales.jdbctest.Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO extends BaseDataAccessObject<Order> {

    private static final String SELECT_ALL = "SELECT " +
            "    o.id, p.name, o.price, o.quantity, " +
            "    (o.price * o.quantity) AS total_amount, " +
            "    CONCAT(c.firstname, ' ', c.lastname) AS customer_name, " +
            "    CONCAT(s.firstname, ' ', s.lastname) AS sales_person, " +
            "    o.created AS sales_date, o.customer_id, o.sales_id, o.product_id " +
            "FROM orders AS o" +
            "    JOIN customer AS c ON c.id = o.customer_id " +
            "    JOIN customer AS s ON s.id = o.sales_id " +
            "    JOIN product AS p ON p.id = o.product_id ";
    private static final String SELECT_ONE = SELECT_ALL + " WHERE o.id = ?";
    private static final String UPDATE_QUERY = "UPDATE orders SET product_id = ?, " +
            "customer_id = ?, sales_id = ?, price = ?, quantity = ? " +
            "WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product (" +
            "product_id, customer_id, sales_id, price, quantity, created" +
            ") VALUES (?,?,?,?,?,NOW())";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";
    private static final String MARK_AS_PAID_QUERY = "UPDATE orders SET paid = NOW() WHERE id = ?";

    public OrdersDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Order findById(long id) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ONE);
        ) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            Order order = new Order();
            while (rs.next()) {
                order = populateOrder(rs);
            }

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> items = new ArrayList<>();

        try (
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                Order order = populateOrder(rs);
                items.add(order);
            }

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Order> findAllByCustomer(long customer_id) {
        List<Order> items = new ArrayList<>();
        String query = SELECT_ALL + " WHERE o.customer_id = ? ";

        try (
                PreparedStatement statement = this.connection.prepareStatement(query);
        ) {
            statement.setLong(1, customer_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                Order order = populateOrder(rs);
                items.add(order);
            }

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Order> findAllBySales(long sales_id) {
        List<Order> items = new ArrayList<>();
        String query = SELECT_ALL + " WHERE o.sales_id = ? ";

        try (
                PreparedStatement statement = this.connection.prepareStatement(query);
        ) {
            statement.setLong(1, sales_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                Order order = populateOrder(rs);
                items.add(order);
            }

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Order populateOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setCustomer_id(rs.getLong("customer_id"));
        order.setSalesId(rs.getLong("sales_id"));
        order.setProductId(rs.getLong("product_id"));
        order.setPrice(rs.getDouble("price"));
        order.setQuantity(rs.getInt("quantity"));
        order.setProductName(rs.getString("name"));
        order.setCustomerName(rs.getString("customer_name"));
        order.setSalesPerson(rs.getString("sales_person"));
        order.setSalesDate(rs.getString("sales_date"));

        return order;
    }

    @Override
    public Order update(Order dto) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setLong(1, dto.getProductId());
            statement.setLong(2, dto.getCustomerId());
            statement.setLong(3, dto.getSalesId());
            statement.setDouble(4, dto.getPrice());
            statement.setInt(5, dto.getQuantity());
            statement.setLong(6, dto.getId());

            statement.execute();
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order create(Order dto) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setLong(1, dto.getProductId());
            statement.setLong(2, dto.getCustomerId());
            statement.setLong(3, dto.getSalesId());
            statement.setDouble(4, dto.getPrice());
            statement.setInt(5, dto.getQuantity());
            statement.execute();

            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {

    }

    /**
     *
     * @param id long
     */
    public void markAsPaid(long id) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(MARK_AS_PAID_QUERY);
        ) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
