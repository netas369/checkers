import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Checkers {
    private static final int BOARD_SIZE = 8;
    private static final int NUM_INITIAL_ROWS = 3;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_A_PIECE = 'a';
    private static final char PLAYER_B_PIECE = 'b';
    private static final char PLAYER_A_KING = 'A';
    private static final char PLAYER_B_KING = 'B';
    private static char[][] board = new char[8][8];


    private static void initializeBoard() {
        int i;
        int j;
        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 8; ++j) {
                board[i][j] = ' ';
            }
        }

        for(i = 0; i < 3; ++i) {
            if (i % 2 == 0) {
                for(j = 1; j < 8; j += 2) {
                    board[i][j] = 'a';
                }
            } else {
                for(j = 0; j < 8; j += 2) {
                    board[i][j] = 'a';
                }
            }
        }

        for(i = 7; i > 4; --i) {
            if (i % 2 == 0) {
                for(j = 1; j < 8; j += 2) {
                    board[i][j] = 'b';
                }
            } else {
                for(j = 0; j < 8; j += 2) {
                    board[i][j] = 'b';
                }
            }
        }
    }
    private static void printBoard() {
        int i;
        for(i = 1; i < 9; ++i) {
            System.out.print("   " + i);
        }

        System.out.print("\n  ");

        for(i = 0; i < 8; ++i) {
            System.out.print("+---");
        }

        System.out.print("+\n");

        for(i = 0; i < 8; ++i) {
            System.out.print(i + 1 + " ");

            int i2;
            for(i2 = 0; i2 < 8; ++i2) {
                System.out.print("| " + board[i][i2] + " ");
            }

            System.out.print("|\n  ");

            for(i2 = 0; i2 < 8; ++i2) {
                System.out.print("+---");
            }

            System.out.print("+\n");
        }
    }
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
    }
}