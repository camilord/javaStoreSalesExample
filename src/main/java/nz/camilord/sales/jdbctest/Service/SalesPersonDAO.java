package nz.camilord.sales.jdbctest.Service;

import nz.camilord.sales.jdbctest.Common.BaseDataAccessObject;
import nz.camilord.sales.jdbctest.Common.PersonTypes;
import nz.camilord.sales.jdbctest.Model.SalesPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesPersonDAO extends BaseDataAccessObject<SalesPerson> {

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

    public SalesPersonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public SalesPerson findById(long id) {
        SalesPerson staff = new SalesPerson();
        try(
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ONE);
        ) {
            statement.setLong(1, id);
            statement.setString(2, PersonTypes.STAFF.toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                staff.setId(rs.getLong("id"));
                staff.setFirstname(rs.getString("firstname"));
                staff.setLastname(rs.getString("lastname"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setAddress(rs.getString("address"));
                staff.setMobile(rs.getString("mobile"));
            }

            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SalesPerson> findAll()
    {
        List<SalesPerson> items = new ArrayList<>();

        try(
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ALL);
        ) {
            statement.setString(1, PersonTypes.STAFF.toString());
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                SalesPerson staff = new SalesPerson();
                staff.setId(rs.getLong("id"));
                staff.setFirstname(rs.getString("firstname"));
                staff.setLastname(rs.getString("lastname"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setAddress(rs.getString("address"));
                staff.setMobile(rs.getString("mobile"));

                items.add(staff);
            }

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public SalesPerson update(SalesPerson dto) {
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
            statement.setString(8, PersonTypes.STAFF.toString());
            statement.execute();

            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public SalesPerson create(SalesPerson dto) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY);
        ) {
            statement.setString(1, dto.getFirstname());
            statement.setString(2, dto.getLastname());
            statement.setString(3, dto.getAddress());
            statement.setString(4, dto.getPhone());
            statement.setString(5, dto.getMobile());
            statement.setString(6, dto.getEmail());
            statement.setString(7, PersonTypes.STAFF.toString());
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
            statement.setString(2, PersonTypes.STAFF.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
