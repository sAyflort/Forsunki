package ru.shihov.forsunki.fors.solver;

import java.util.HashMap;
import java.util.List;

public class FrenelUtil {

    public static OutputResult getFrenels(List<InputProperties> inputPropertiesListOx, List<InputProperties> inputPropertiesListFuel, double step, double epsilon) {
        try {
            double sumOx = 0;
            for (InputProperties ip:
                    inputPropertiesListOx) {
                sumOx += getResultFrenelIntegral(ip.getX1(), ip.getX2(), step, epsilon) * getResultFrenelIntegral(ip.getY1(), ip.getY2(), step, epsilon);
            }
            double sumFuel = 0;
            for (InputProperties ip:
                    inputPropertiesListFuel) {
                sumFuel= getResultFrenelIntegral(ip.getX1(), ip.getX2(), step, epsilon) * getResultFrenelIntegral(ip.getY1(), ip.getY2(), step, epsilon);
            }
            return new OutputResult(sumOx, sumFuel, "OK");
        } catch (RuntimeException e) {
            return new OutputResult(0,0, e.getMessage());
        }
    }

    private static double getResultFrenelIntegral(double x1, double x2, double step, double epsilon) throws RuntimeException{
        if(x2 <= x1) {
            throw new RuntimeException("Некорректный интервал. Значение x1 (или y2) должно быть меньше x2 (или y2)");
        }
        double sum = 0;
        while(x1 <= x2) {
            sum+=getValuePrefrenelFunction(x1, step)*epsilon;
            x1+=epsilon;
        }
        return 2*sum/Math.PI;
    }

    private static double getValuePrefrenelFunction(double x, double step) {
        return Math.pow(Math.E, Math.pow(-x/(Math.sqrt(2)*step), 2));
    }
}
