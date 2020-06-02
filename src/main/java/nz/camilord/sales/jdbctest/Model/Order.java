package nz.camilord.sales.jdbctest.Model;

import nz.camilord.sales.jdbctest.Common.DataTransferObjectInterface;

public class Order implements DataTransferObjectInterface {

    private long id;
    private long product_id;
    private long customer_id;
    private long sales_id;
    private double price;
    private int quantity;
    private String paid;

    private String name;
    private double total_amount;
    private String customer_name;
    private String sales_person;
    private String sales_date;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return product_id;
    }

    public void setProductId(long product_id) {
        this.product_id = product_id;
    }

    public long getCustomerId() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getSalesId() {
        return sales_id;
    }

    public void setSalesId(long sales_id) {
        this.sales_id = sales_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public double getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getSalesPerson() {
        return sales_person;
    }

    public void setSalesPerson(String sales_person) {
        this.sales_person = sales_person;
    }

    public String getSalesDate() {
        return sales_date;
    }

    public void setSalesDate(String sales_date) {
        this.sales_date = sales_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", customer_id=" + customer_id +
                ", sales_id=" + sales_id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", paid='" + paid + '\'' +
                ", name='" + name + '\'' +
                ", total_amount=" + total_amount +
                ", customer_name='" + customer_name + '\'' +
                ", sales_person='" + sales_person + '\'' +
                ", sales_date='" + sales_date + '\'' +
                '}';
    }
}
