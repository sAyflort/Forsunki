package ru.shihov.forsunki.fors.solver;

import java.util.List;

public class FrenelUtil {

    public static OutputResult getFrenels(List<InputProperties> inputPropertiesListOx, List<InputProperties> inputPropertiesListFuel, double step, double epsilon) {
        try {
            double sumOx = 0;
            for (InputProperties ip:
                    inputPropertiesListOx) {
                sumOx += (ip.getFlow()*ip.getQuantity()/4)*getResultFrenelIntegral(ip.getX1(), ip.getX2(), step, epsilon)*getResultFrenelIntegral(ip.getY1(), ip.getY2(), step, epsilon);
            }
            double sumFuel = 0;
            for (InputProperties ip:
                    inputPropertiesListFuel) {
                sumFuel += (ip.getFlow()*ip.getQuantity()/4)*getResultFrenelIntegral(ip.getX1(), ip.getX2(), step, epsilon)*getResultFrenelIntegral(ip.getY1(), ip.getY2(), step, epsilon);
            }
            return new OutputResult(sumOx, sumFuel, "OK");
        } catch (RuntimeException e) {
            return new OutputResult(0,0, e.getMessage());
        }
    }

    private static double getResultFrenelIntegral(double x1, double x2, double step, double epsilon) throws RuntimeException{
        if (x1 > x2) throw new RuntimeException("Некорректные координаты. Левая граница больше правой");
        double z = x1/(Math.sqrt(2)*step);
        double sum = 0;
        while(z < x2/(Math.sqrt(2)*step)) {
            sum+=getValuePrefrenelFunction(z)*epsilon;
            z+=epsilon;
        }
        return 2*sum/Math.sqrt(Math.PI);
    }

    private static double getValuePrefrenelFunction(double z) {
        return Math.pow(Math.E, (-1)*Math.pow(z, 2));
    }
}
