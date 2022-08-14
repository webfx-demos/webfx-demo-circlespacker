package dev.webfx.demo.ledpacking;

import dev.webfx.extras.led.Led;
import dev.webfx.lib.circlepacking.CirclePackingPane;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
public class LedPackingApplication extends Application {

    // Used to bing all led highlighted properties together
    private final BooleanProperty highlightedProperty = new SimpleBooleanProperty();

    @Override
    public void start(Stage primaryStage) {
        SplitPane verticalSplitPane;
        primaryStage.setScene(new Scene(verticalSplitPane = new SplitPane(
                new SplitPane(createLedPackingPane(5, Color.CADETBLUE), createLedPackingPane(8, Color.BLUEVIOLET)),
                createLedPackingPane(13, Color.SANDYBROWN)
        ), 850, 450));
        verticalSplitPane.setOrientation(Orientation.VERTICAL);
        primaryStage.setTitle("Led Packing");
        primaryStage.show();
    }

    private CirclePackingPane createLedPackingPane(int count, Color ledColor) {
        CirclePackingPane ledPackingPane = new CirclePackingPane(createLeds(count, ledColor));
        ledPackingPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return ledPackingPane;
    }

    private Node[] createLeds(int count, Color color) {
        return IntStream.range(0, count).mapToObj(i -> createLed(color.brighter().brighter())).toArray(Node[]::new);
    }

    private Led createLed(Color ledColor) {
        Led led = Led.create(ledColor);
        led.highlightedProperty().bind(highlightedProperty);
        led.setOnAction(e -> highlightedProperty.set(!highlightedProperty.get()));
        return led;
    }
}
