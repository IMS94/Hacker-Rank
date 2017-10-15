package hackerrank.ieeeextreme11.trying;

import java.util.Scanner;

import static hackerrank.ieeeextreme11.trying.Cell.BOB;
import static hackerrank.ieeeextreme11.trying.Cell.EMPTY;
import static hackerrank.ieeeextreme11.trying.Cell.ME;

class CheerUpBob {

    private Cell[][] board = new Cell[3][3];
    private int[][] preferences = new int[9][2];

    private void read() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < board.length; i++) {
            preferences[i][0] = scanner.nextInt() - 1;
            preferences[i][1] = scanner.nextInt() - 1;
        }
    }

    private Move findMyNextMove(Cell[][] board) {
        Stats best = new Stats(EMPTY, 1000);
        Move worstMove = new Move(-1, -1);

        // Traverse all cells, evalutae minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("----------------- (%d,%d) -----------------------\n", i, j);
                // Check if cell is empty
                if (board[i][j].equals(EMPTY)) {
                    // Make the move
                    board[i][j] = ME;

                    // compute evaluation function for this move.
                    Stats moveStats = minimax(board, 1, true);

                    // Undo the move
                    board[i][j] = EMPTY;

                    // If the value of the current move is
                    // more than the best value, then update best
                    if (moveStats.winner == BOB) {
                        worstMove.row = i;
                        worstMove.col = j;
                        best = moveStats;
                    }
                }
            }
        }

        return worstMove;
    }

    /**
     * This is the minimax function. It considers all the possible ways the game can go and returns the value of the
     * board
     *
     * @param depth how many turns have gone so far
     * @param isBob whether the current move is for Bob
     * @return best stats in the favor of Bob
     */
    private Stats minimax(Cell board[][], int depth, boolean isBob) {
        printBoard();
        Cell winner = evaluate();

        // If Maximizer/Bob has won the game return his/her evaluated score
        if (winner == BOB) {
            System.out.println("BOB won");
            return new Stats(BOB, depth);
        }

        // If Minimizer/I has won the game return his/her evaluated score
        if (winner == ME) {
            System.out.println("BOB won");
            return new Stats(ME, depth);
        }

        // If there are no more moves and no winner then it is a tie
        if (!isMovesLeft()) {
            System.out.println("Game Over - No moves left");
            return new Stats(EMPTY, depth);
        }

        Stats best = new Stats(EMPTY, depth);

        // If this Bob's move
        if (isBob) {
            for (int i = 0; i < 9; i++) {
                int row = preferences[i][0];
                int col = preferences[i][1];
                if (board[row][col] == EMPTY) {
                    System.out.printf("BOB putting at (%d,%d)", row, col);
                    // Make the move
                    board[row][col] = BOB;
                    // Call minimax recursively and choose the maximum value
                    best = max(best, minimax(board, depth + 1, false));
                    // Undo the move
                    board[row][col] = EMPTY;
                    break;
                }
            }
        } else {
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == EMPTY) {
                        System.out.printf("I'm putting at (%d.%d)\n", i, j);
                        // Make the move
                        board[i][j] = ME;
                        // Call minimax recursively and choose the maximum value
                        best = max(best, minimax(board, depth + 1, true));

                        // Undo the move
                        board[i][j] = EMPTY;
                    }
                }
            }
        }

        return best;
    }

    /**
     * Chose best stats for a given game at two instances
     *
     * @param currentBest currentBest
     * @param s2          s2
     * @return better stats instance
     */
    private Stats max(Stats currentBest, Stats s2) {
        if (currentBest.winner == BOB) {
            if (s2.winner == BOB) {
                return currentBest.moves < s2.moves ? currentBest : s2;
            } else {
                return currentBest;
            }
        } else if (s2 != null && s2.winner == BOB) {
            return s2;
        } else {
            return currentBest;
        }
    }


    /**
     * This is the evaluation function as discussed in the previous article ( http://goo.gl/sJgv68 )
     *
     * @return 0- if no one has won | 10 if Bob has won | -10 if I have won
     */
    private Cell evaluate() {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0].equals(BOB))
                    return BOB;
                else if (board[row][0].equals(ME))
                    return ME;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == BOB)
                    return BOB;
                else if (board[0][col] == ME)
                    return ME;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == BOB)
                return BOB;
            else if (board[0][0] == ME)
                return ME;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == BOB)
                return BOB;
            else if (board[0][2] == ME)
                return ME;
        }

        // Else if none of them have won then return 0
        return null;
    }


    /**
     * This function returns true if there are moves remaining on the board. It returns false if there are no moves left
     * to play.
     *
     * @return true if any cell is empty
     */
    private boolean isMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (board[i][j].equals(EMPTY)) {
                    return true;
                }
        }
        return false;
    }


    private Move getBobsNextMove() {
        // Bob makes the move
        for (int i = 0; i < preferences.length; i++) {
            int row = preferences[i][0];
            int col = preferences[i][1];

            if (board[row][col].equals(EMPTY)) {
                return new Move(row, col);
            }
        }

        return new Move(-1, -1);
    }

    private void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf("%2s", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Plays the game. Bob first. Then me.
     */
    public void play() {
        // Populate board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = EMPTY;
            }
        }

        for (int i = 0; i < 9; i++) {
            // Bob making the move
            Move bobsMove = getBobsNextMove();
            if (bobsMove.col == -1 && bobsMove.row == -1) {
                break;
            }
            board[bobsMove.row][bobsMove.col] = BOB;

            // Now, I'm making my move
            Move myMove = findMyNextMove(board);
            if (myMove.col == -1 && myMove.row == -1) {
                System.out.println("Breaking ...");
                break;
            }

            board[myMove.row][myMove.col] = ME;
            System.out.println(myMove);
            printBoard();
        }
    }

    public static void main(String[] args) {
        CheerUpBob cheerUpBob = new CheerUpBob();
        cheerUpBob.read();
        cheerUpBob.play();
    }

}

class Stats {
    Cell winner;
    int moves;

    public Stats(Cell winner, int moves) {
        this.winner = winner;
        this.moves = moves;
    }
}

class Move {
    int row;
    int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return String.format("[%d %d]", row, col);
    }
}

enum Cell {
    BOB("x"),
    ME("o"),
    EMPTY("_");

    private String name;

    Cell(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}