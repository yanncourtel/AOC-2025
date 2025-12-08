package santamarket.model;

public class Receipt {
    private double totalPrice = 0.0;
    private double totalDiscount = 0.0;

    public void addProduct(double price) {
        totalPrice += price;
    }

    public void addDiscount(double discount) {
        totalDiscount += discount;
    }

    public double getTotalPrice() {
        return totalPrice - totalDiscount;
    }
}
