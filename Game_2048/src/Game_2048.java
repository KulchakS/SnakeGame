import java.util.Scanner;

public class Game_2048 {

    public static int SIDE = 4;
    public static int score = 0;
    public static String str;

    public static boolean isCreated = false;

    public static Scanner scanner = new Scanner(System.in);

    public static int[][] game = new int[SIDE][SIDE];

    public static void main(String[] args) {
        createNewNumber();
        createNewNumber();
        while(!isCreated) {
            if(!canUserMove()) {
                gameOver();
            }
            for(int x = 0; x < SIDE; x++) {
                for(int y = 0; y < SIDE; y++) {
                    System.out.print(game[x][y] + "   " + "\t");
                }
                System.out.println();
                System.out.println();
            }
            str = scanner.nextLine();
            if(str.equals("w") || str.equals("W")) {
                moveUp();
            }
            if(str.equals("a") || str.equals("A")) {
                moveLeft();
            }
            if(str.equals("s") || str.equals("S")) {
                moveDown();
            }
            if(str.equals("d") || str.equals("D")) {
                moveRight();
            }
            if(str.equals(" ") || str.equals("")) {
                continue;
            }
            if(checkMaxValue() == 2048) {
                win();
            }
            createNewNumber();
        }
    }

    private static void rotateClockwise() {
        int[][] result = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                result[j][SIDE - 1 - i] = game[i][j];
            }
        }
        game = result;
    }

    private static void moveLeft() {
        boolean isNewNumberNeeded = false;
        for (int[] row : game) {
            boolean wasCompressed = compressRow(row);
            boolean wasMerged = mergeRow(row);
            if (wasMerged) {
                compressRow(row);
            }
            if (wasCompressed || wasMerged) {
                isNewNumberNeeded = true;
            }
        }
    }

    private static void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private static void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private static void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private static void win() {
        isCreated = true;
        System.out.println("You Win!");
        System.out.println(score);
    }

    private static void gameOver() {
        isCreated = true;
        System.out.println("Game Over!");
        System.out.println(score);
    }

    private static boolean canUserMove() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (game[y][x] == 0) {
                    return true;
                } else if (y < SIDE - 1 && game[y][x] == game[y + 1][x]) {
                    return true;
                } else if ((x < SIDE - 1) && game[y][x] == game[y][x + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void createNewNumber() {
        boolean test = false;
        do{
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            int t = (int) (Math.random() * 10);
            if(game[x][y] == 0) {
                game[x][y] = t < 9 ? 2 : 4;
            }
            test = true;
        }
        while(!test);
    }

    private static int checkMaxValue() {
        int maxValue = 0;
        for(int x = 0; x < SIDE; x++) {
            for(int y = 0; y < SIDE; y++) {
                if(maxValue < game[x][y]) {
                    maxValue = game[x][y];
                }
            }
        }
        return maxValue;
    }

    private static boolean compressRow(int[] row) {
        int insertPosition = 0;
        boolean result = false;
        for (int x = 0; x < SIDE; x++) {
            if (row[x] > 0) {
                if (x != insertPosition) {
                    row[insertPosition] = row[x];
                    row[x] = 0;
                    result = true;
                }
                insertPosition++;
            }
        }
        return result;
    }

    private static boolean mergeRow(int[] row) {
        boolean result = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0 && row[i] == row[i + 1]) {
                row[i] += row[i + 1];
                row[i + 1] = 0;
                result = true;
                score += row[i];
            }
        }
        return result;
    }
}
