/* Klassen RaffleCup gør så man kan rulle
   mere end 1 terning af gangen i dette tilfælde 5
 */
package models;

public class RaffleCup {
    private Die[] dice = new Die[5]; /* Array til at holde fem terninger
                                        og ikke fire, fordi vi angiver hvor mange
                                        pladser der skal være i arrayet
                                        og ikke hvad arrayet tæller til 0, 1, 2, 3, 4
                                      */

    // Default constructor der initialiserer koppen med fem terninger
    public RaffleCup() {
        for (int index = 0; index < dice.length; index++) {
            dice[index] = new Die();
        }
    }

    // Kaster alle terningerne i koppen
    public void throwDice() {
        for (Die die : dice) {
            die.roll();
        }
    }

    // returnerer arrayet dice for at få terninge resultaterne fra throwDice()
    public Die[] getDice() {
        return dice;
    }
}
