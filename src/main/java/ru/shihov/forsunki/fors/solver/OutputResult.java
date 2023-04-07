package ru.shihov.forsunki.fors.solver;

public class OutputResult {
    private final double sumOx;
    private final double sumFuel;
    private final double sumOxDivSumFuel;
    private final String message;

    public OutputResult(double sumOx, double sumFuel, String message) {
        this.sumOx = sumOx;
        this.sumFuel = sumFuel;
        this.message = message;
        sumOxDivSumFuel = sumOx/sumFuel;
    }

    public double getSumOx() {
        return sumOx;
    }

    public double getSumFuel() {
        return sumFuel;
    }

    public double getSumOxDivSumFuel() {
        return sumOxDivSumFuel;
    }

    public String getMessage() {
        return message;
    }
}
