package dev.webfx.demo.circlespacker;

import dev.webfx.extras.led.Led;
import dev.webfx.lib.circlepacking.CirclePackingPane;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
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
                new SplitPane(createCirclesPackerPane(5, Color.CADETBLUE), createCirclesPackerPane(8, Color.BLUEVIOLET)),
                createCirclesPackerPane(13, Color.SANDYBROWN)
        ), 850, 450));
        verticalSplitPane.setOrientation(Orientation.VERTICAL);
        primaryStage.setTitle("Circles Packer");
        primaryStage.show();
    }

    CirclePackingPane createCirclesPackerPane(int count, Color color) {
        CirclePackingPane circlePackingPane = new CirclePackingPane(createCircles(count, color));
        circlePackingPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return circlePackingPane;
    }

    private static Node[] createCircles(int count, Color color) {
        return IntStream.range(0, count).mapToObj(i -> Led.create(color.brighter().brighter(), null, null)).toArray(Node[]::new);
    }
}
