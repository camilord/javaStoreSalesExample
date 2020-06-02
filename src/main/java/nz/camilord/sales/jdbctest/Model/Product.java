package nz.camilord.sales.jdbctest.Model;

import nz.camilord.sales.jdbctest.Common.DataTransferObjectInterface;

public class Product implements DataTransferObjectInterface
{
    private long id;
    private String name;
    private double price;
    private int stock;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
