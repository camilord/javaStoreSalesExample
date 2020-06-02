package nz.camilord.sales.jdbctest.Model;

import nz.camilord.sales.jdbctest.Common.DataTransferObjectInterface;
import nz.camilord.sales.jdbctest.Common.PersonTypes;

public class SalesPerson extends Person implements DataTransferObjectInterface {
    @Override
    public String getType() {
        return PersonTypes.STAFF.toString();
    }
}
