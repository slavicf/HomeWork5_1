package star;

public class Point {    // Хранит в себе x,y(float или double) координаты точки в двухмерном пространстве.
    public double x;
    public double y;

    public Point() {
        this(0,0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

