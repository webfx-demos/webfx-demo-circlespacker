package dev.webfx.demo.circlespacker;

import dev.webfx.lib.circlespacker.CirclesPackerPane;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.stream.IntStream;

/**
 * @author Bruno Salmon
 */
public class CirclesPackerApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        SplitPane verticalSplitPane;
        primaryStage.setScene(new Scene(verticalSplitPane = new SplitPane(
                new SplitPane(new CirclesPackerPane(createCircles(5, Color.CADETBLUE)), new CirclesPackerPane(createCircles(8, Color.BLUEVIOLET))),
                new CirclesPackerPane(createCircles(13, Color.SANDYBROWN))
        ), 850, 450));
        verticalSplitPane.setOrientation(Orientation.VERTICAL);
        primaryStage.setTitle("Circles Packer");
        primaryStage.show();
    }

    private static Circle[] createCircles(int count, Color color) {
        return IntStream.range(0, count).mapToObj(i -> new Circle(0, color)).toArray(Circle[]::new);
    }

}
