package car;

public class CarOwner {
    private double balance;
    private String name;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public CarOwner(double balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}
