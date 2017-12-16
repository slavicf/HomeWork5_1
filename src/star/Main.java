package star;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    //  --------------------------- declarations ---------------------------------------
    private static final double WINDOW_WIDTH = 400;     // Ширина окна
    private static final double WINDOW_HEIGHT = 400;    // Высота окна
    private static final double WINDOW_HALF_WIDTH = WINDOW_WIDTH / 2;   // Середина окна по ширине
    private static final double WINDOW_HALF_HEIGHT = WINDOW_HEIGHT / 2;   // Середина окна по высоте
    private static final int CORNERS = 5 * 2;
    private static final double DEGREE = 360 / CORNERS;
    private static final double ASPECT = 0.382;
    private static Point[] corners = new Point[CORNERS];
    private static Point center = new Point();

    private static double degree = -90;
    private static double radius = 200;
    private static double x1, y1, x2, y2;
    private static int i, j, k, l;

    public static void main(String[] args) {
        launch(args);
    }

    void windowSetup(Stage primaryStage) {
        primaryStage.setTitle("Star");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
    }

    private void draw(Pane root) {
        for (i = 0; i < CORNERS; i++) {
            x1 = radius * Math.cos(Math.toRadians(degree));
            y1 = radius * Math.sin(Math.toRadians(degree));
            corners[i++] = new Point(x1, y1);
            degree += DEGREE;
            x2 = radius * ASPECT * Math.cos(Math.toRadians(degree));
            y2 = radius * ASPECT * Math.sin(Math.toRadians(degree));
            corners[i] = new Point(x2, y2);
            degree += DEGREE;
        }

//        for (int i = 0; i < POINTS; i++) {
//            j = cyclePoint(i, 2, POINTS);
//        }

        for (i = 0; i < CORNERS; i++) {
            j = (i < CORNERS - 1) ? i + 1 : 0;
            x1 = corners[i].x + center.x;
            y1 = corners[i].y + center.y;
            x2 = corners[j].x + center.x;
            y2 = corners[j].y + center.y;

            Line line = new Line(x1, y1, x2, y2);
            root.getChildren().addAll(line);              // Строим
        }

    }

        @Override
    public void start(Stage primaryStage) throws Exception {
            Pane root = new Pane();
            Scene scene = new Scene(root);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите координату X центра звезды: ");
            center.x = scanner.nextInt();
            System.out.print("Введите координату Y центра звезды: ");
            center.y = scanner.nextInt();
            System.out.print("Введите радиус звезды: ");
            radius = scanner.nextInt();

            windowSetup(primaryStage);                      // Инициализация окна

            draw(root);                                     // Построение

            primaryStage.setScene(scene);
            primaryStage.show();
        }
}
