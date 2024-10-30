/* Klassen Die laver en enkelt terning
   der kan rulles for at få en "tilfældig" værdi mellem 1 og 6
 */
package models;

import java.util.Random;

public class Die {
    private int eyes = 0; // Gemmer terningens værdi
    private final Random random = new Random(); // Random objekt til at rulle terningen

    /**
     * Creates a new Die object, with face set to eyes. Used for test purpose
     *
     * @param eyes value should be between 1 and 6
     */
    public Die(int eyes) {
        this.eyes = eyes;
    }

    // Default constructor der initialiserer terningen uden at give den en specifik værdi
    public Die() {
    }

    // Ruller terningen for at få et tall mellem 1 og 6
    public void roll() {
        this.eyes = random.nextInt(6) + 1;
    }

    // returnerer den rullede værdi
    public int getEyes() {
        return eyes;
    }
}
