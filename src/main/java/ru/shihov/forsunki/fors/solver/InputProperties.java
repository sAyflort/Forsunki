package ru.shihov.forsunki.fors.solver;

public class InputProperties {
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double flow;
    private final int quantity;

    public InputProperties(double x1, double x2, double y1, double y2, double flow, int quantity) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.flow = flow;
        this.quantity = quantity;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getFlow() {
        return flow;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "InputProperties{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", flow=" + flow +
                ", quantity=" + quantity +
                '}';
    }
}
