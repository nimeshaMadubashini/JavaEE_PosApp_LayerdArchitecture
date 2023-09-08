package lk.ijse.pos.entity;

public class Item {
    private String code;
    private String name;
    private double qty;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Item() {
    }

    public Item(String code, String name, double qty, double price) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }


}
