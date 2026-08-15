abstract class FoodOrder {

    private int orderId;
    private String customerName;
    private double amount;

    static String restaurantName = "Foodies Restaurant";
    static int orderCount = 0;

    public FoodOrder(int orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        orderCount++;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract double calculateDeliveryCharge();

    public static void displayTotalOrders() {
        System.out.println("Total Orders: " + orderCount);
    }
}


interface Discountable {
    double applyDiscount();
}


class RegularOrder extends FoodOrder implements Discountable {

    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    public double calculateDeliveryCharge() {
        return 80;
    }

    public double applyDiscount() {
        return getAmount() * 0.10;
    }
}


class PremiumOrder extends FoodOrder implements Discountable {

    public PremiumOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    public double calculateDeliveryCharge() {
        return 50;
    }

    public double applyDiscount() {
        return getAmount() * 0.15;
    }
}


class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static void generateOrderSummary(FoodOrder order) {

        Discountable d = (Discountable) order;

        double discount = d.applyDiscount();
        double delivery = order.calculateDeliveryCharge();

        double finalAmount =
                order.getAmount() - discount + delivery;

        System.out.println("----------------------------");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Bill: Rs. " + order.getAmount());
        System.out.println("Discount: Rs. " + discount);
        System.out.println("Delivery Charge: Rs. " + delivery);
        System.out.println("Final Payable: Rs. " + finalAmount);
        System.out.println("----------------------------");
    }
}


public class Main {

    public static void main(String[] args) {

        System.out.println("Restaurant: " + FoodOrder.restaurantName);

        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(1, "Jasleen", 500);
        orders[1] = new PremiumOrder(2, "Aman", 1000);
        orders[2] = new RegularOrder(3, "Simran", 700);
        orders[3] = new PremiumOrder(4, "Karan", 1200);
        orders[4] = new RegularOrder(5, "Harpreet", 600);
        orders[5] = new PremiumOrder(6, "Neha", 900);

        for (FoodOrder order : orders) {

            if (OrderUtility.validateCustomerName(order.getCustomerName())
                    && OrderUtility.validateAmount(order.getAmount())) {

                OrderUtility.generateOrderSummary(order);

            } else {
                System.out.println("Invalid Order");
            }
        }

        FoodOrder.displayTotalOrders();
    }
}
