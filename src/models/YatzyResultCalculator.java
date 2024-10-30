/* Klassen YatzyResultCalculator bruges til at beregne scoren
   for kast med 5 terninger
 */
package models;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {
    private Die[] dice; // Array af terninger der bruges til at beregne scoren


    /**
     * @param dice
     */
    // constructor der initialiserer YaztyResultCalculator med et array af terninger
    public YatzyResultCalculator(Die[] dice) {
        this.dice = dice;
    }

    /**
     * Calculates the score for Yatzy uppersection
     *
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        int score = 0;
        for (Die die : dice) {
            if (die.getEyes() == eyes) {
                score += die.getEyes();
            }
        }
        return score;
    }

    // beregner scoren for et par
    public int onePairScore() {
        return oneThreeFourCalculator(2);
    }

    // beregner scoren for to par
    public int twoPairScore() {
        return findTwoPairs();
    }

    // beregner scoren for tre ens
    public int threeOfAKindScore() {
        return oneThreeFourCalculator(3);
    }

    // beregner scoren for fire ens
    public int fourOfAKindScore() {
        return oneThreeFourCalculator(4);
    }

    /* beregner scoren for en lille straight det vil sige 1-5
       give summen af terningerne i point
     */
    public int smallStraightScore() {
        return checkStraight(new int[]{1, 2, 3, 4, 5}) ? 15 : 0;
    }

    /* beregner scoren for en lille straight det vil sige 2-6
       giver summen af terningerne i point
     */
    public int largeStraightScore() {
        return checkStraight(new int[]{2, 3, 4, 5, 6}) ? 20 : 0;
    }

    // beregner scoren for fuldt hus give summen af tre ens plus to ens i point
    public int fullHouseScore() {
        int[] eyeCounts = countEyes();
        int threeOfAKind = 0;
        int twoOfAKind = 0;

        for (int index = 0; index < eyeCounts.length; index++) {
            if (eyeCounts[index] == 3) {
                threeOfAKind = (index + 1) * 3;
            } else if (eyeCounts[index] == 2) {
                twoOfAKind = (index + 1) * 2;
            }
        }

        if (threeOfAKind > 0 && twoOfAKind > 0) {
            return threeOfAKind + twoOfAKind;
        }
        return 0;
    }

    // beregner scoren for chancen det vil sige summen af alle terninger
    public int chanceScore() {
        int score = 0;
        for (Die die : dice) {
            score += die.getEyes();
        }
        return score;
    }

    // beregner om alle fem terninger har samme værdi og i så fald giver 50 point
    public int yatzyScore() {
        int firstValue = dice[0].getEyes();
        for (Die die : dice) {
            if (die.getEyes() != firstValue) {
                return 0;
            }
        }
        return 50;
    }

    /* metode til at finde en, tre eller fire ens
       tag @param count, som er antallet af ens værdier der søges
       @return den højste score for det specifikke antal ens værdider
     */
    private int oneThreeFourCalculator(int count) {
        int[] eyesCounts = new int[6];
        for (Die die : dice) {
            int eyeValue = die.getEyes();
            if (eyeValue >= 1 && eyeValue <= 6) {
                eyesCounts[eyeValue - 1]++;
            }
        }

        int maxScore = 0;
        for (int index = 0; index < eyesCounts.length; index++) {
            if (eyesCounts[index] >= count) {
                int currentScore = (index + 1) * count;
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }
            }
        }
        return maxScore;
    }

    // metode til at finde to par @return scoren for to par ellers 0 hvis der ikke er to par
    private int findTwoPairs() {
        int[] eyeCounts = new int[6];
        for (Die die : dice) {
            int eyeValue = die.getEyes();
            if (eyeValue >= 1 && eyeValue <= 6) {
                eyeCounts[eyeValue - 1]++;
            }
        }
        int pairs = 0;
        int score = 0;
        for (int index = 0; index < eyeCounts.length; index++) {
            if (eyeCounts[index] >= 2) {
                pairs++;
                score += (index + 1) * 2;
                //stopper løkken hvis 2 par er fundet
                if (pairs == 2) {
                    break;
                }
            }
        }
        return pairs == 2 ? score : 0;
    }

    /* metode til at tjekke om der en straight
       tag @param straight, som er et int array med værdier der udgør en straight (1-5 eller 2-6)
       @return true hvis alle værdier for en straight findes ellers retunere den false
     */
    private boolean checkStraight(int[] straight) {
        for (int value : straight) {
            boolean found = false;
            for (Die die : dice) {
                if (die.getEyes() == value) {
                    found = true;
                    break;

                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    /* metode til at tælle forekomster af hver terningeværdi
       @return et array med antallet af gange en terning værdi blev slået.
       Værdier fra 1 til 6 i dette tilfælde og med 5 fem terninger
     */
    private int[] countEyes() {
        int[] eyeCounts = new int[6];
        for (Die die : dice) {
            int eyeValue = die.getEyes();
            if (eyeValue >= 1 && eyeValue <= 6) {
                eyeCounts[eyeValue - 1]++;
            }
        }
        return eyeCounts;
    }
}
