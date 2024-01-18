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

    private static void processPlayerTurn(int player, Scanner in) {
        boolean x = false;
        boolean once = true;
        int row1 = 0;
        byte column1 = 0;

        while(!x) {
            ArrayList<Path> cellsGo = new ArrayList();
            x = true;

            for(int i = 0; i < 8; ++i) {
                for(int j = 0; j < 8; ++j) {
                    if (!once) {
                        i = row1;
                        j = column1;
                    }

                    Cell now = new Cell(i, j);
                    if (player == 0 && board[i][j] == 'a') {
                        if (i < 6 && j > 1 && (board[i + 1][j - 1] == 'b' || board[i + 1][j - 1] == 'B') && board[i + 2][j - 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i + 2, j - 2)));
                            x = false;
                        }

                        if (i < 6 && j < 6 && (board[i + 1][j + 1] == 'b' || board[i + 1][j + 1] == 'B') && board[i + 2][j + 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i + 2, j + 2)));
                            x = false;
                        }
                    } else if (player == 1 && board[i][j] == 'b') {
                        if (i > 1 && j > 1 && (board[i - 1][j - 1] == 'a' || board[i - 1][j - 1] == 'A') && board[i - 2][j - 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i - 2, j - 2)));
                            x = false;
                        }

                        if (i > 1 && j < 6 && (board[i - 1][j + 1] == 'a' || board[i - 1][j + 1] == 'A') && board[i - 2][j + 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i - 2, j + 2)));
                            x = false;
                        }
                    } else if (player == 0 && board[i][j] == 'A') {
                        if (i > 1 && j > 1 && (board[i - 1][j - 1] == 'b' || board[i - 1][j - 1] == 'B') && board[i - 2][j - 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i - 2, j - 2)));
                            x = false;
                        }

                        if (i > 1 && j < 6 && (board[i - 1][j + 1] == 'b' || board[i - 1][j + 1] == 'B') && board[i - 2][j + 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i - 2, j + 2)));
                            x = false;
                        }

                        if (i < 6 && j > 1 && (board[i + 1][j - 1] == 'b' || board[i + 1][j - 1] == 'B') && board[i + 2][j - 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i + 2, j - 2)));
                            x = false;
                        }

                        if (i < 6 && j < 6 && (board[i + 1][j + 1] == 'b' || board[i + 1][j + 1] == 'B') && board[i + 2][j + 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i + 2, j + 2)));
                            x = false;
                        }
                    } else if (player == 1 && board[i][j] == 'B') {
                        if (i > 1 && j > 1 && (board[i - 1][j - 1] == 'a' || board[i - 1][j - 1] == 'A') && board[i - 2][j - 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i - 2, j - 2)));
                            x = false;
                        }

                        if (i > 1 && j < 6 && (board[i - 1][j + 1] == 'a' || board[i - 1][j + 1] == 'A') && board[i - 2][j + 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i - 2, j + 2)));
                            x = false;
                        }

                        if (i < 6 && j > 1 && (board[i + 1][j - 1] == 'a' || board[i + 1][j - 1] == 'A') && board[i + 2][j - 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i + 2, j - 2)));
                            x = false;
                        }

                        if (i < 6 && j < 6 && (board[i + 1][j + 1] == 'a' || board[i + 1][j + 1] == 'A') && board[i + 2][j + 2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i + 2, j + 2)));
                            x = false;
                        }
                    }

                    if (!once) {
                        break;
                    }
                }

                if (!once) {
                    break;
                }
            }

            if (x) {
                break;
            }

            once = false;
            printBoard();
        }

        if (once) {
            printBoard();
        }

    }

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
    }
}

class Cell {
    private int row;
    private int col;

    Cell(int r, int c) {
        this.row = r;
        this.col = c;
    }
}

class Path {
    private Cell start;
    private Cell end;

    Path(Cell s, Cell e) {
        this.start = s;
        this.end = e;
    }
}