package dev.webfx.demo.circlespacker;

import dev.webfx.lib.circlespacker.CirclesPackerPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
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

    CirclesPackerPane createCirclesPackerPane(int count, Color color) {
        CirclesPackerPane circlesPackerPane = new CirclesPackerPane(createCircles(count, color));
        circlesPackerPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return circlesPackerPane;
    }

    private static Node[] createCircles(int count, Color color) {
        return IntStream.range(0, count).mapToObj(i -> LedButton.create(color.brighter().brighter(), null, null)).toArray(Node[]::new);
    }

    static final class LedButton extends Region {

        private final Circle ledBorder = new Circle(), ledCentre = new Circle(), highlight = new Circle();
        private final Line hLine = new Line(), vLine = new Line();
        private final Paint pressedFill, releasedFill;
        private final InnerShadow innerShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), 0, 0, 0, 0);

        LedButton(Color ledColor, Boolean plus) {
            Paint borderFill = new LinearGradient( 0,  0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0,  Color.rgb( 20,  20,  20, 0.65)),
                    new Stop(0.15, Color.rgb( 20,  20,  20, 0.65)),
                    new Stop(0.26, Color.rgb( 41,  41,  41, 0.65)),
                    new Stop(0.4,  Color.rgb(100, 100, 100, 0.80)),
                    new Stop(1.0,  Color.rgb( 20,  20,  20, 0.65)));
            ledBorder.setFill(borderFill);
            pressedFill = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0,  ledColor.deriveColor(0d, 1d, 0.77, 1d)),
                    new Stop(0.49, ledColor.deriveColor(0d, 1d, 0.5,  1d)),
                    new Stop(1.0,  ledColor));
            releasedFill = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0,  ledColor.deriveColor(0d, 1d, 0.57, 1d)),
                    new Stop(0.49, ledColor.deriveColor(0d, 1d, 0.4,  1d)),
                    new Stop(1.0,  ledColor.deriveColor(0d, 1d, 0.2,  1d)));
            ledCentre.setFill(releasedFill);
            ledCentre.setEffect(innerShadow);
            ledCentre.setOnMousePressed(e -> {
                ledCentre.setFill(pressedFill);
                ledCentre.setEffect(new DropShadow(ledCentre.getRadius() * 0.3, ledColor));
            });
            ledCentre.setOnMouseReleased(e -> {
                ledCentre.setFill(releasedFill);
                ledCentre.setEffect(null);
            });

            Color lineColor = Color.IVORY;
            hLine.setStroke(lineColor);
            hLine.setStrokeLineCap(StrokeLineCap.ROUND);
            vLine.setStroke(lineColor);
            vLine.setStrokeLineCap(StrokeLineCap.ROUND);
            Node sign = plus == Boolean.TRUE ? new Group(hLine, vLine) : hLine;
            sign.setOpacity(0.6);
            getChildren().setAll(ledBorder, ledCentre, highlight, sign);
            highlight.setMouseTransparent(true);
            sign.setMouseTransparent(true);
            if (plus == null)
                sign.setVisible(false);
        }

        void setOnAction(EventHandler<ActionEvent> actionHandler) {
            ledCentre.setOnMouseClicked(e -> actionHandler.handle(new ActionEvent(this, this)));
        }

        @Override public void layoutChildren() {
            double width = getWidth();
            double height = getHeight();
            double radius = Math.min(width, height) / 2;
            ledBorder.setRadius(radius);
            ledCentre.setRadius(0.8 * radius);
            highlight.setRadius(0.7 * radius);
            innerShadow.setRadius(0.8 * 0.075 / 0.15 * radius);
            Paint highlightFill = new RadialGradient(0, 0, 0 - highlight.getRadius(), 0 - highlight.getRadius(), highlight.getRadius(), false,
                    CycleMethod.NO_CYCLE, new Stop(0.0, Color.WHITE), new Stop(1.0, Color.TRANSPARENT));
            highlight.setFill(highlightFill);
            double lineLength = 0.4 * radius;
            hLine.setStartX(width / 2 - lineLength);
            hLine.setEndX(width / 2 + lineLength);
            hLine.setStrokeWidth(0.2 * radius);
            vLine.setStartY(width / 2 - lineLength);
            vLine.setEndY(width / 2 + lineLength);
            vLine.setStrokeWidth(0.2 * radius);
            for (Node child : getChildren())
                if (!(child instanceof Group))
                    layoutInArea(child, 0, 0, width, height, 0 , HPos.CENTER, VPos.CENTER);
            layoutInArea(hLine, 0, 0, width, height, 0 , HPos.CENTER, VPos.CENTER);
            layoutInArea(vLine, 0, 0, width, height, 0 , HPos.CENTER, VPos.CENTER);
        }

        static LedButton create(Color ledColor, Boolean plus, Runnable actionHandler) {
            LedButton ledButton = new LedButton(ledColor, plus);
            ledButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            ledButton.setOnAction(e -> actionHandler.run());
            return ledButton;
        }
    }
}
