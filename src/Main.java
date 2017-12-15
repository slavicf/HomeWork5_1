import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Main extends Application{
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGTH = 640;
    private static final int RGB_PATTERN = 6;

    private Random rnd = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    private void windowSetup (Stage primaryStage) {
        Pane root = new Pane();
        Scene scene =  new Scene(root);

        primaryStage.setTitle("Snowman");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGTH);
        primaryStage.setScene(scene);
    }

    private Paint generateRandomColor() {
        String rgb = "#";
        int iColor;
        char cColor;

        for(int i = 0; i < RGB_PATTERN; i++) {
            iColor = rnd.nextInt(15);

            rgb += (char)('0' + r.nextInt(9));
        }
        System.out.println(rgb);
        return Paint.valueOf(rgb);
    }

    private void draw (int circleNumber, int circleMinRradius, int circleMaxRradius) {
        int iX, iY, iR, iC;
        for(int i = 0; i < circleNumber; i++) {

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int circleNumber, circleMinRradius, circleMaxRradius;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество кругов: ");
        circleNumber = scanner.nextInt();
        System.out.print("Минимальный радиус круга: ");
        circleMinRradius = scanner.nextInt();
        System.out.print("Максимальный радиус круга: ");
        circleMaxRradius = scanner.nextInt();

        windowSetup(primaryStage);

        primaryStage.show();
    }
}
