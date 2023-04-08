package ru.shihov.forsunki.fors.solver;

import java.util.List;

public class FrenelUtil {

    public static OutputResult getFrenels(List<InputProperties> inputPropertiesListOx, List<InputProperties> inputPropertiesListFuel, double step, double epsilon) {
        try {
            double sumOx = 0;
            for (InputProperties ip:
                    inputPropertiesListOx) {
                sumOx += (ip.getFlow()/4)*(getResultFrenelIntegral(ip.getX2(),step, epsilon) - getResultFrenelIntegral(ip.getX1(),step, epsilon))*
                        (getResultFrenelIntegral(ip.getY2(),step, epsilon) - getResultFrenelIntegral(ip.getY1(),step, epsilon));
            }
            double sumFuel = 0;
            for (InputProperties ip:
                    inputPropertiesListFuel) {
                sumFuel += (ip.getFlow()/4)*(getResultFrenelIntegral(ip.getX2(),step, epsilon) - getResultFrenelIntegral(ip.getX1(),step, epsilon))*
                        (getResultFrenelIntegral(ip.getY2(),step, epsilon) - getResultFrenelIntegral(ip.getY1(),step, epsilon));
            }
            return new OutputResult(sumOx, sumFuel, "OK");
        } catch (RuntimeException e) {
            return new OutputResult(0,0, e.getMessage());
        }
    }

    private static double getResultFrenelIntegral(double x, double step, double epsilon) throws RuntimeException{
        double sign = 1;
        double temp = 0;
        if(x < 0) {
            x*=-1;
            sign = -1;
        }
        x = x/(Math.sqrt(2)*step);
        double sum = 0;
        while(temp < x) {
            sum+=getValuePrefrenelFunction(temp, step)*epsilon;
            temp+=epsilon;
        }
        return sign*2*sum/Math.sqrt(Math.PI);
    }

    private static double getValuePrefrenelFunction(double x, double step) {
        return Math.pow(Math.E, (-1)*Math.pow(x/(Math.sqrt(2)*step), 2));
    }
}
