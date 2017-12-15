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
        String rgb = "#";

        for (int i = 0; i < RGB_PATTERN; i++) {
            String temp = Integer.toHexString(rnd.nextInt(COLOR_SPAN));
            rgb += (temp.length() == 2) ? temp : '0' + temp;
        }
        return Paint.valueOf(rgb);
    }

    private Circle generateCircle(int minRadius, int maxRadius) {
        Circle c = new Circle(rnd.nextInt(WINDOW_WIDTH),
                rnd.nextInt(WINDOW_HEIGHT),
                rnd.nextInt(maxRadius));

        c.setStroke(generateColor());
        c.setFill(Paint.valueOf("#00000000"));

        return c;
    }

    private void draw(Pane root, int circlesNumber, int circleMinRradius, int circleMaxRradius) {
        int iX, iY, iR, iC;
        for (int i = 0; i < circlesNumber; i++) {
            Circle circle = generateCircle(circleMinRradius, circleMaxRradius);
            root.getChildren().addAll(circle);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        int circleNumber, circleMinRradius, circleMaxRradius;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество кругов: ");
        circleNumber = scanner.nextInt();
        System.out.print("Минимальный радиус круга: ");
        circleMinRradius = scanner.nextInt();
        System.out.print("Максимальный радиус круга: ");
        circleMaxRradius = scanner.nextInt();

        windowSetup(primaryStage);
        draw(root, circleNumber, circleMinRradius, circleMaxRradius);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
