package view.controls;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FireAnimation extends Transition {
    private ImageView imageView;
    private int bufferX = 0;
    private int bufferY = 0;
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private final double MAX_SIZE_RATE = 1.4;

    public FireAnimation(ImageView imageView) {
        this.imageView = imageView;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        imageView.setLayoutX(++bufferX);
        imageView.setLayoutY(++bufferY);
        bufferX = Math.max(bufferX , MAX_X);
        bufferY = Math.max(bufferY , MAX_Y);
        int size = (int) (Math.random() * (MAX_SIZE_RATE * 10));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size/10 * imageView.getFitWidth());
    }
}
