package nz.camilord.sales.jdbctest.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDataAccessObject <T extends DataTransferObjectInterface>
{
    protected final Connection connection;
    protected final static String LAST_VAL_QUERY = "SELECT last_value FROM ";

    protected BaseDataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    public abstract T findById(long id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(long id);

    /**
     * get last id in the db on newly inserted row
     * @param table_name String
     * @return int
     */
    protected int getLastVal(String table_name) {
        int key = 0;
        String q = LAST_VAL_QUERY + table_name;
        try (
                Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(q);
            while(rs.next()) {
                key = rs.getInt(1);
            }
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
