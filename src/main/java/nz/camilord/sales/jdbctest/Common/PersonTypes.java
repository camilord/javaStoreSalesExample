package nz.camilord.sales.jdbctest.Common;

public enum PersonTypes
{
    CUSTOMER("customer"), STAFF("staff");

    private String description;

    PersonTypes(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
