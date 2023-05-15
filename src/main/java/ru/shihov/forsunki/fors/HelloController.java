package ru.shihov.forsunki.fors;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.shihov.forsunki.fors.solver.FrenelUtil;
import ru.shihov.forsunki.fors.solver.InputProperties;
import ru.shihov.forsunki.fors.solver.OutputResult;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TextArea inputArea;

    @FXML
    private TextField epsilonField;

    @FXML
    private TextField stepField;

    @FXML
    private TextArea outputArea;

    @FXML
    protected void onStartResult() {
        String input = inputArea.getText();
        double epsilon;
        double step;
        try {
            epsilon = Double.parseDouble(epsilonField.getText());
        } catch (NumberFormatException n) {
            epsilon = 0.0001;
        }

        try {
            step = Double.parseDouble(stepField.getText());
        } catch (NumberFormatException n) {
            outputArea.appendText("Не указан шаг форсунок!\n");
            return;
        }

        outputArea.clear();

        List<InputProperties> listOx = new ArrayList<>();
        List<InputProperties> listFuel = new ArrayList<>();

        String[] inputs = input.split("\\n");
        for (String str:
             inputs) {
            try {
                String[] boarder = str.split(" ")[0].split(",");

                double flow =  Double.parseDouble(str.split("=")[1].split(" ")[0]);

               /* if(Math.abs(flow - 0.2167) < 0.0001) {
                    flow = 0.137219;
                } else if (Math.abs(flow - 0.4108) < 0.0001) {
                    flow = 0.485248;
                } else if (Math.abs(flow - 0.007) < 0.0001) {
                    flow = 0.00536;
                } else if (Math.abs(flow - 0.029) < 0.0001) {
                    flow = 0.03734;
                } else  {
                    flow = 0;
                }*/

                InputProperties ip = new InputProperties(
                        Double.parseDouble(boarder[0])/**13/12*/,
                        Double.parseDouble(boarder[1])/**13/12*/,
                        Double.parseDouble(boarder[2])/**13/12*/,
                        Double.parseDouble(boarder[3])/**13/12*/,
                        //Double.parseDouble(str.split("=")[1].split(" ")[0]),
                        flow,
                        Integer.parseInt(str.split("n=")[1])
                        );
                //TODO del
                String m = str.contains("mOx") ? "mOx=" : "mFuel=";
                //TODO del
                System.out.println(ip.getX1()+","+ip.getX2()+","+ip.getY1()+","+ip.getY2() + " " + m + ip.getFlow() + " n=" + ip.getQuantity());
                if (str.contains("mOx")) {
                    listOx.add(ip);
                } else if (str.contains("mFuel")) {
                    listFuel.add(ip);
                } else {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                outputArea.appendText(String.format("Некорректный ввод в строке: %s\\n", str));
            }
        }

        OutputResult outputResult = FrenelUtil.getFrenels(listOx, listFuel, step, epsilon);
        if(!outputResult.getMessage().equals("OK")) {
            outputArea.appendText(outputResult.getMessage());
        } else {
            outputArea.appendText("mOx=" + outputResult.getSumOx()+"\n"+ "mFuel=" + outputResult.getSumFuel()+"\n");
            outputArea.appendText("Km="+outputResult.getSumOxDivSumFuel());
        }
    }

}