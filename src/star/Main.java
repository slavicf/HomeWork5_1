package star;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    //  --------------------------- declarations ---------------------------------------
    private static final double WINDOW_WIDTH = 400;             // Ширина окна
    private static final double WINDOW_HEIGHT = 400;            // Высота окна
    private static final int VERTEX_NUM = 5 * 2;                // Количество вершин звезды (внешних и внутренних)
    private static final double ANGLE = 360 / VERTEX_NUM;       // Угол поворота
    private static final double RATIO = 2.6180339887498955;     // Соотношение радиусов внешних углов к внутренним

    private static Point center = new Point();                  // Центр звезды
    private static double radius = 200;                         // Радиус звезды
    private static double degree = -90;                         // Начальный угол
    private static Point[] vertex = new Point[VERTEX_NUM];      // Массив вершин

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
        int i, j;
        double startX, startY, endX, endY;

        for (i = 0; i < VERTEX_NUM; i++) {
            vertex[i] = new Point(radius * Math.cos(Math.toRadians(degree)),
                    radius * Math.sin(Math.toRadians(degree)));
            degree += ANGLE;
            vertex[++i] = new Point(radius / RATIO * Math.cos(Math.toRadians(degree)),
                    radius / RATIO * Math.sin(Math.toRadians(degree)));
            degree += ANGLE;
        }

        for (i = 0; i < VERTEX_NUM; i++) {
            j = (i < VERTEX_NUM - 1) ? i + 1 : 0;
            startX = vertex[i].x + center.x;
            startY = vertex[i].y + center.y;
            endX = vertex[j].x + center.x;
            endY = vertex[j].y + center.y;
            Line line = new Line(startX, startY,endX, endY);
            root.getChildren().addAll(line);              // Строим
        }

    }

        @Override
    public void start(Stage primaryStage) throws Exception {
            Pane root = new Pane();
            Scene scene = new Scene(root);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите координату X центра звезды: ");     center.x = scanner.nextInt();
            System.out.print("Введите координату Y центра звезды: ");     center.y = scanner.nextInt();
            System.out.print("Введите радиус звезды: ");                  radius = scanner.nextInt();

            windowSetup(primaryStage);                      // Инициализация окна

            draw(root);                                     // Построение

            primaryStage.setScene(scene);
            primaryStage.show();
        }
}
