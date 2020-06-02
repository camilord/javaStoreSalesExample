package nz.camilord.sales.jdbctest.Service;

import nz.camilord.sales.jdbctest.Common.BaseDataAccessObject;
import nz.camilord.sales.jdbctest.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends BaseDataAccessObject<Product> {

    private static final String SELECT_ONE = "SELECT * FROM product WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE product SET name = ?," +
            "price = ?, stock = ? WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product (" +
            "name, price, stock, created" +
            ") VALUES (?,?,?,NOW())";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM product";

    public ProductDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Product findById(long id) {
        Product product = new Product();
        try (
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ONE);
        ) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> items = new ArrayList<>();
        try (
                PreparedStatement statement = this.connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));

                items.add(product);
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Product dto) {
        Product product = new Product();
        try (
                PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setString(1, dto.getName());
            statement.setDouble(2, dto.getPrice());
            statement.setInt(3, dto.getStock());
            statement.setLong(4, dto.getId());
            statement.execute();

            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product create(Product dto) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERY);
        ) {
            statement.setString(1, dto.getName());
            statement.setDouble(2, dto.getPrice());
            statement.setInt(3, dto.getStock());
            statement.execute();

            int insert_id = this.getLastVal("product");
            dto.setId(insert_id);

            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        Product product = new Product();
        try (
                PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
