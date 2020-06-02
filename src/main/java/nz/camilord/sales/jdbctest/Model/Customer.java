package nz.camilord.sales.jdbctest.Model;

import nz.camilord.sales.jdbctest.Common.DataTransferObjectInterface;
import nz.camilord.sales.jdbctest.Common.PersonTypes;

public class Customer extends Person implements DataTransferObjectInterface
{
    public String getType() {
        return PersonTypes.CUSTOMER.toString();
    }
}
