package snowman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Main extends Application {
//  --------------------------- declarations ---------------------------------------
    private static final double WINDOW_WIDTH = 400;     // Ширина окна
    private static final double WINDOW_HEIGHT = 800;    // Высота окна
    private static final double WINDOW_HALF_WIDTH = WINDOW_WIDTH / 2;   // Середина окна по ширине
    private static final double BOTTOM_OFFSET = 30; // Стартовое смещение от нижнего края окна
    private static final double RGB_PATTERN = 3;    // Шаблон RGB состоит из 3-х цветов
    private static final int COLOR_SPAN = 200;      // Диапазон 0-255 умылшенно срезан для получения более тёмных цветов

    private static double mark = WINDOW_HEIGHT - BOTTOM_OFFSET;   // Нижняя метка отрисовки круга
    private static double radius;       // Радиус текущего круга
    private static int count;           // Количество кругов
    private static int minRadius;       // Минимальный радиус круга
    private static int maxRadius;       // Максимальный радиус круга

    private Random rnd = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    void windowSetup(Stage primaryStage) {
        primaryStage.setTitle("Snowman");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
    }

    private Paint generateColor() {
        StringBuilder rgb = new StringBuilder("#");     // Инициализируем строку RGB
        for (int i = 0; i < RGB_PATTERN; i++) {
            String temp = Integer.toHexString(rnd.nextInt(COLOR_SPAN)); // Получаем случайный цвет с конвертацией в строку
            if (temp.length() == 1) rgb.append(0);      // Если число однозначное, то восполняем лидирующий ноль
            rgb.append(temp);       // Формируем строку шаблона
        }
        return Paint.valueOf(rgb.toString());
    }

    private Circle generateCircle(boolean bottomShift) {
        radius = rnd.nextInt(maxRadius - minRadius) + minRadius;
        if (bottomShift) mark -= radius;      // Смещаем метку вверх перед получением круга на его радиус
        Circle circle = new Circle(WINDOW_HALF_WIDTH, mark, radius);
        if (bottomShift) mark -= radius;      // Смещаем метку вверх после получения круга на его радиус

        circle.setStroke(generateColor());
        circle.setStrokeWidth(2);
        circle.setFill(Color.TRANSPARENT);
        return circle;
    }

    private void draw(Pane root) {
        Circle circle;
        for (int i = 0; i < count; i++) {
            circle = generateCircle(true);
            root.getChildren().addAll(circle);
        }

        mark += radius;             // Выставляем метку по центру круга
        final double DEGREE = 30;    // Угол глаз от горизонтальной линии проходящей через центр круга
        double eyeDistance = 0.55 * radius;    // Дистанция глаз от центра круга
        double mouthDistance = 0.3 * radius;   // Дистанция рта от центра круга

        double eyeX = eyeDistance * Math.cos(Math.toRadians(DEGREE));       // Смещение глаза по X
        double eyeY = mark - eyeDistance * Math.sin(Math.toRadians(DEGREE));    // Смещение глаза по Y
        minRadius = (int) radius / 10;      // Пропорции глаз относительно радиуса головы
        maxRadius = (int) radius / 3;       // Пропорции глаз относительно радиуса головы

        circle = generateCircle(false);       //
        circle.setCenterX(WINDOW_HALF_WIDTH - eyeX);    //
        circle.setCenterY(eyeY);                        //
        root.getChildren().addAll(circle);              // Строим левый глаз

        circle = generateCircle(false);       //
        circle.setCenterX(WINDOW_HALF_WIDTH + eyeX);    //
        circle.setCenterY(eyeY);                        //
        root.getChildren().addAll(circle);              // Строим правый глаз

        circle = generateCircle(false);       //
        circle.setCenterY(mark + mouthDistance);        //
        root.getChildren().addAll(circle);              // Строим рот

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество кругов: ");
        count = scanner.nextInt();
        System.out.print("Минимальный радиус круга: ");
        minRadius = scanner.nextInt();
        System.out.print("Максимальный радиус круга: ");
        maxRadius = scanner.nextInt();

        windowSetup(primaryStage);                      // Инициализация окна

        draw(root);                                     // Построение снеговика

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
