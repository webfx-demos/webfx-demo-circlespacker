package dev.webfx.demo.circlespacker;

import dev.webfx.lib.circlespacker.CirclesPackerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author Bruno Salmon
 */
public class CirclesPackerApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new SplitPane(createMostlyFluidPane(0), createMostlyFluidPane(100)), 850, 450));
        primaryStage.setTitle("Circles Packer");
        primaryStage.show();
    }

    private static Pane createMostlyFluidPane(double hueShift) {
        Circle a1 = createArea(Color.rgb(0, 52, 118), hueShift);
        Circle a2 = createArea(Color.rgb(0, 98, 210), hueShift);
        Circle a3 = createArea(Color.rgb(180, 210, 247), hueShift);
        Circle a4 = createArea(Color.rgb(213, 223, 239), hueShift);
        Circle a5 = createArea(Color.rgb(223, 225, 229), hueShift);
        return new CirclesPackerPane(a1, a2, a3, a4, a5);
    }

    private static Circle createArea(Color color, double hueShift) {
        Circle area = new Circle();
        area.setFill(color.deriveColor(hueShift, 1, 1, 1));
        return area;
    }
}
