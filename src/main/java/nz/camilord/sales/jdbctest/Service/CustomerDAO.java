package nz.camilord.sales.jdbctest.Service;

import nz.camilord.sales.jdbctest.Common.BaseDataAccessObject;
import nz.camilord.sales.jdbctest.Common.PersonTypes;
import nz.camilord.sales.jdbctest.Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDataAccessObject<Customer> {

    private static final String SELECT_ONE = "SELECT * FROM customer WHERE id = ? AND type = ?";
    private static final String UPDATE_QUERY = "UPDATE customer SET firstname = ?," +
            "lastname = ?, address = ?, phone = ?, mobile = ?, email = ? " +
            "WHERE id = ? AND type = ?";
    private static final String INSERT_QUERY = "INSERT INTO customer (" +
            "firstname, lastname, address, phone, mobile, email, type, created" +
            ") VALUES (?,?,?,?,?,?,?,NOW())";
    private static final String DELETE_QUERY = "DELETE FROM customer " +
            "WHERE id = ? AND type = ?";
    private static final String SELECT_ALL = "SELECT * FROM customer WHERE type = ?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(long id) {
        Customer customer = new Customer();
        try(
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ONE);
        ) {
            statement.setLong(1, id);
            statement.setString(2, PersonTypes.CUSTOMER.toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                customer.setId(rs.getLong("id"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setMobile(rs.getString("mobile"));
            }

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> items = new ArrayList<>();

        try(
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ALL);
        ) {
            statement.setString(1, PersonTypes.CUSTOMER.toString());
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setMobile(rs.getString("mobile"));

                items.add(customer);
            }

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer update(Customer dto) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setString(1, dto.getFirstname());
            statement.setString(2, dto.getLastname());
            statement.setString(3, dto.getAddress());
            statement.setString(4, dto.getPhone());
            statement.setString(5, dto.getMobile());
            statement.setString(6, dto.getEmail());
            statement.setLong(7, dto.getId());
            statement.setString(8, PersonTypes.CUSTOMER.toString());
            statement.execute();

            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer create(Customer dto) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY);
        ) {
            statement.setString(1, dto.getFirstname());
            statement.setString(2, dto.getLastname());
            statement.setString(3, dto.getAddress());
            statement.setString(4, dto.getPhone());
            statement.setString(5, dto.getMobile());
            statement.setString(6, dto.getEmail());
            statement.setString(7, PersonTypes.CUSTOMER.toString());
            statement.execute();

            int insert_id = this.getLastVal("customer");
            dto.setId(insert_id);

            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setLong(1, id);
            statement.setString(2, PersonTypes.CUSTOMER.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
