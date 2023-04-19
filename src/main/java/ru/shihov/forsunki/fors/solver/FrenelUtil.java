package ru.shihov.forsunki.fors.solver;

import java.util.List;

public class FrenelUtil {

    public static OutputResult getFrenels(List<InputProperties> inputPropertiesListOx, List<InputProperties> inputPropertiesListFuel, double step, double epsilon) {
        try {
            double sumOx = 0;
            for (InputProperties ip:
                    inputPropertiesListOx) {
                sumOx += (ip.getFlow()/4*ip.getQuantity())*getResultFrenelIntegral(ip.getX1(), ip.getX2(), step, epsilon)*getResultFrenelIntegral(ip.getY1(), ip.getY2(), step, epsilon);
            }
            double sumFuel = 0;
            for (InputProperties ip:
                    inputPropertiesListFuel) {
                sumFuel += (ip.getFlow()/4*ip.getQuantity())*getResultFrenelIntegral(ip.getX1(), ip.getX2(), step, epsilon)*getResultFrenelIntegral(ip.getY1(), ip.getY2(), step, epsilon);
            }
            return new OutputResult(sumOx, sumFuel, "OK");
        } catch (RuntimeException e) {
            return new OutputResult(0,0, e.getMessage());
        }
    }

    private static double getResultFrenelIntegral(double x1, double x2, double step, double epsilon) throws RuntimeException{
        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x2);
        x1 = x1/(Math.sqrt(2)*step);
        x2 = x2/(Math.sqrt(2)*step);
        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x2);
        double temp = x1;
        double sum = 0;
        while(temp < x2) {
            sum+=getValuePrefrenelFunction(temp)*epsilon;
            temp+=epsilon;
        }
        return 2*sum/Math.sqrt(Math.PI);
    }

    private static double getValuePrefrenelFunction(double x) {
        return Math.pow(Math.E, (-1)*Math.pow(x, 2));
    }
}
