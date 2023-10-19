import java.util.Random;
public class Sneak extends Enemy {
    public Sneak() {
        super("Yılan", 4, generatedamage(), 12, 0);
    }

    private static int generatedamage() {
        int max = 6;
        int min = 3;
        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }
}