import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 640;
    private static final int RGB_PATTERN = 3;
    private static final int COLOR_SPAN = 255;

    private Random rnd = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    private void windowSetup(Stage primaryStage) {
        primaryStage.setTitle("Snowman");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
    }

    private Paint generateColor() {
        StringBuilder rgb = new StringBuilder("#");
        for (int i = 0; i < RGB_PATTERN; i++) {
            String temp = Integer.toHexString(rnd.nextInt(COLOR_SPAN));
            rgb.append((temp.length() == 2) ? temp : '0' + temp);
        }
        return Paint.valueOf(rgb.toString());
    }

    private Circle generateCircle(int minRadius, int maxRadius) {
        int iRadius = rnd.nextInt(maxRadius - minRadius) + minRadius;
        Circle c = new Circle(0, WINDOW_WIDTH / 2, iRadius);

        c.setStroke(generateColor());
        c.setFill(Paint.valueOf("#00000000"));

        return c;
    }

    private void draw(Pane root, Circle circles[], int circleMinRradius, int circleMaxRradius) {
        for (Circle circle: circles) {
            circle = generateCircle(circleMinRradius, circleMaxRradius);
            root.getChildren().addAll(circle);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        int countOfCircles, circleMinRradius, circleMaxRradius;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество кругов: ");
        countOfCircles = scanner.nextInt();
        System.out.print("Минимальный радиус круга: ");
        circleMinRradius = scanner.nextInt();
        System.out.print("Максимальный радиус круга: ");
        circleMaxRradius = scanner.nextInt();

        Circle[] circles = new Circle[countOfCircles];

        windowSetup(primaryStage);
        draw(root, circles, circleMinRradius, circleMaxRradius);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
