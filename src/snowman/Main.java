package snowman;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private static final double WIDTH = 400;     // Ширина окна
    private static final double HEIGHT = 800;    // Высота окна
    private static final double HALF_WIDTH = WIDTH / 2;   // Середина окна по ширине
    private static final int COLOR_SPAN = 200;      // Диапазон 0-255 умылшенно срезан для получения более тёмных цветов
    private static final double HBOX_WIDTH = 180;
    private static final double HBOX_HEIGHT = 25;
    private static final double HBOX_X = WIDTH - HBOX_WIDTH;
    private static final double HBOX_Y = 10;    // Свойства HBox
    private static final double BUTTON_WIDTH = HBOX_WIDTH;
    private static final double BUTTON_HEIGHT = 20;
    private static final double BUTTON_X = WIDTH - BUTTON_WIDTH;
    private static final double BUTTON_Y = 85;

    private static double mark = HEIGHT;// Нижняя метка отрисовки круга
    private static double radius;       // Радиус текущего круга
    private static int count;           // Количество кругов
    private static int minRadius;       // Минимальный радиус круга
    private static int maxRadius;       // Максимальный радиус круга
    private static Circle[] circles;    // Максимальный радиус круга

    private Random rnd = new Random(); //  declarations

    public static void main(String[] args) {
        launch(args);
    }

    void windowSetup(Stage primaryStage) {
        primaryStage.setTitle("Snowman");
//        primaryStage.getIcons().add(new Image("http://www.endlessicons.com/wp-content/uploads/2013/12/snowman-icon-614x460.png"));
        Image icon = new Image(getClass().getResourceAsStream("images/snowman.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
//        primaryStage.setWidth(WIDTH);
//        primaryStage.setHeight(HEIGHT);
    }

    Label label(String text) {
        Label label = new Label(text);
        return label;
    } // Создаём Label

    TextField addTextField(String text) {
        TextField textField = new TextField(text);
        textField.setPrefColumnCount(2);
        textField.setAlignment(Pos.CENTER_RIGHT);
        return textField;
    } // Создаём TextField

    HBox hBox(double y) {
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setLayoutX(HBOX_X);
        hBox.setLayoutY(HBOX_Y + HBOX_HEIGHT * y);
        hBox.setPrefSize(HBOX_WIDTH, HBOX_HEIGHT);
        return hBox;
    } // Создаём HBox

    Button button(String text, double x, double y) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        return button;
    } // Создаём Button

    void controls(Pane root, Pane pane) {

        Label lblCount = label("Количество кругов:");
        TextField tfCount = addTextField("5");
        HBox hbCount = hBox(0);
        hbCount.getChildren().addAll(lblCount, tfCount);
        root.getChildren().add(hbCount);

        Label lblMinRadius = label("Минимальный радиус:");
        TextField tfMinRadius = addTextField("10");
        HBox hbMinRadius = hBox(1);
        hbMinRadius.getChildren().addAll(lblMinRadius, tfMinRadius);
        root.getChildren().add(hbMinRadius);

        Label lblMaxRadius = label("Максимальный радиус:");
        TextField tfMaxRadius = addTextField("50");
        HBox hbMaxRadius = hBox(2);
        hbMaxRadius.getChildren().addAll(lblMaxRadius, tfMaxRadius);
        root.getChildren().add(hbMaxRadius);

        Button btn1 = button("Нарисовать снеговика", BUTTON_X, 85);
        btn1.setOnAction(event -> {
            count = Integer.parseInt(tfCount.getText());
            minRadius = Integer.parseInt(tfMinRadius.getText());
            maxRadius = Integer.parseInt(tfMaxRadius.getText());
            mark = HEIGHT;                      // Нижняя метка отрисовки круга
            draw(pane);                         // Построение снеговика
        });

        Button btn2 = button("Покрасить все круги в красный", BUTTON_X, 110);
        btn2.setOnAction(event -> {
            for (int i = 0; i < count; i++) {
                circles[i].setFill(Color.RED);
            }
        });

        Button btn3 = button("Gradient", BUTTON_X, 135);
        btn3.setOnAction(event -> {
            int gradientStep = 220 / (count - 1);
            int color;

            for (int i = 0; i < count; i++) {
                color = gradientStep * i;
                circles[i].setFill(Color.rgb(color, color, color));
            }
        });

        root.getChildren().addAll(btn1, btn2, btn3);
    }

    private Paint generateColor() {
        int red = rnd.nextInt(COLOR_SPAN);
        int green = rnd.nextInt(COLOR_SPAN);
        int blue = rnd.nextInt(COLOR_SPAN);
        return Color.rgb(red, green, blue);
    }

    private Circle generateCircle(boolean bottomShift) {
        radius = rnd.nextInt(maxRadius - minRadius) + minRadius;
        if (bottomShift) mark -= radius;      // Смещаем метку вверх перед получением круга на его радиус
        Circle circle = new Circle(HALF_WIDTH, mark, radius);
        if (bottomShift) mark -= radius;      // Смещаем метку вверх после получения круга на его радиус

        circle.setStroke(generateColor());
        circle.setStrokeWidth(2);
        circle.setFill(Color.TRANSPARENT);
        return circle;
    }

    private void face(Pane pane) {
        mark += radius;             // Выставляем метку по центру круга
        final double DEGREE = 30;    // Угол глаз от горизонтальной линии проходящей через центр круга
        double eyeDistance = 0.55 * radius;    // Дистанция глаз от центра круга
        double mouthDistance = 0.3 * radius;   // Дистанция рта от центра круга

        double eyeX = eyeDistance * Math.cos(Math.toRadians(DEGREE));       // Смещение глаза по X
        double eyeY = mark - eyeDistance * Math.sin(Math.toRadians(DEGREE));    // Смещение глаза по Y
        minRadius = (int) radius / 10;      // Пропорции глаз относительно радиуса головы
        maxRadius = (int) radius / 3;       // Пропорции глаз относительно радиуса головы

        Circle face = generateCircle(false);       //
        face.setCenterX(HALF_WIDTH - eyeX);    //
        face.setCenterY(eyeY);                        //
        pane.getChildren().add(face);              // Строим левый глаз

        face = generateCircle(false);       //
        face.setCenterX(HALF_WIDTH + eyeX);    //
        face.setCenterY(eyeY);                        //
        pane.getChildren().add(face);              // Строим правый глаз

        face = generateCircle(false);       //
        face.setCenterY(mark + mouthDistance);        //
        pane.getChildren().add(face);              // Строим рот
    }

    private void draw(Pane pane) {
        pane.getChildren().clear();
        Circle[] circle = new Circle[count];
        for (int i = 0; i < count; i++) {
            circle[i] = generateCircle(true);
            pane.getChildren().add(circle[i]);
        }
        circles = circle;

        face(pane);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Pane pane = new Pane();
        root.getChildren().addAll(pane);

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите количество кругов: ");
//        count = scanner.nextInt();
//        System.out.print("Минимальный радиус круга: ");
//        minRadius = scanner.nextInt();
//        System.out.print("Максимальный радиус круга: ");
//        maxRadius = scanner.nextInt();

        windowSetup(primaryStage);                      // Инициализация окна

        controls(root, pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
