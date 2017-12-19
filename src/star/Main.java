package star;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    private static final double WINDOW_WIDTH = 400;         // Ширина окна
    private static final double WINDOW_HEIGHT = 400;        // Высота окна
    private static final int VERTEX_NUM = 5 * 2;            // Количество вершин звезды (внешних и внутренних)
    private static final double ANGLE = 360 / VERTEX_NUM;   // Угол поворота
    private static final double EXT_VERTEX = 1;             // Делитель радиуса внешних вершин
    private static final double INT_VERTEX = 2.618033988749895; // Делитель радиуса внутренних вершин (квадрат золотого сечения)

    private static Point center = new Point();              // Центр звезды
    private static double radius = WINDOW_WIDTH / 2;        // Радиус звезды
    private static double degree = -90;                     // Начальный угол
    private static Point[] vertex = new Point[VERTEX_NUM];  // Массив вершин
    // Объявление констант, переменных, структур

    public static void main(String[] args) {
        launch(args);
    }   // Запуск приложения

    void windowSetup(Stage primaryStage) {
        primaryStage.setTitle("Star");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
    }   // Настройка окна

    private void setVertex() {
        double x, y;
        for (int i = 0; i < VERTEX_NUM; i++) {
            double ratio = (i % 2 == 0) ? EXT_VERTEX : INT_VERTEX; // Для внешних вершин отношение = 1, для внутренних = RATIO
            x = radius / ratio * Math.cos(Math.toRadians(degree));
            y = radius / ratio * Math.sin(Math.toRadians(degree));
            vertex[i] = new Point(x, y);
            degree += ANGLE;
        }
    }   // Заполнение массива вершин звезды

    private void drawStar(Pane root) {
        setVertex();    // Заполнение массива вершин звезды

        for (int i = 0; i < VERTEX_NUM; i++) {
            int j = (i < VERTEX_NUM - 1) ? i + 1 : 0;   // Указатель на конечную точку
            Line line = new Line();
            line.setStartX(vertex[i].x + center.x);
            line.setStartY(vertex[i].y + center.y);
            line.setEndX(vertex[j].x + center.x);
            line.setEndY(vertex[j].y + center.y);
            root.getChildren().addAll(line);
        }
    }   // Отрисовка звезды

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

        drawStar(root);                                     // Построение

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
