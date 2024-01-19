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

    private static CellFactory cellFactory = new ConcreteCellFactory();

    private static Checkers instance; // Singleton instance

    private Checkers() {
        initializeBoard();
    }

    public static Checkers getInstance() {
        if (instance == null) {
            instance = new Checkers();
        }
        return instance;
    }

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

                    Cell now = cellFactory.createCell(i, j);
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

            capturePiece(player, cellsGo, in);
            once = false;
            printBoard();
        }

        if (once) {
            movePiece(player, in);
            printBoard();
        }

    }

    private static void capturePiece(int player, ArrayList<Path> cellsGo, Scanner in) {
        System.out.print("player " + (player + 1) + " please choose one of the cell(s) that show below for next move : \n");
        int y = 1;

        for(Iterator var4 = cellsGo.iterator(); var4.hasNext(); ++y) {
            Path p = (Path)var4.next();
            System.out.println(y + ". " + (p.getStart().getRow() + 1) + "|" + (p.getStart().getColumn() + 1) + " to " + (p.getEnd().getRow() + 1) + "|" + (p.getEnd().getColumn() + 1));
        }

        int tmp = in.nextInt();
        int row1 = ((Path)cellsGo.get(tmp - 1)).getEnd().getRow();
        int column1 = ((Path)cellsGo.get(tmp - 1)).getEnd().getColumn();
        board[row1][column1] = board[((Path)cellsGo.get(tmp - 1)).getStart().getRow()][((Path)cellsGo.get(tmp - 1)).getStart().getColumn()];
        board[((Path)cellsGo.get(tmp - 1)).getStart().getRow()][((Path)cellsGo.get(tmp - 1)).getStart().getColumn()] = ' ';
        int newRow = (((Path)cellsGo.get(tmp - 1)).getStart().getRow() + row1) / 2;
        int newCol = (((Path)cellsGo.get(tmp - 1)).getStart().getColumn() + column1) / 2;
        if (player == 0 && row1 == 7 && board[row1][column1] == 'a') {
            board[row1][column1] = 'A';
        }

        if (player == 1 && row1 == 0 && board[row1][column1] == 'b') {
            board[row1][column1] = 'B';
        }

        board[newRow][newCol] = ' ';
    }

    private static void movePiece(int player, Scanner in) {
        while(true) {
            System.out.print("player " + (player + 1) + " please enter row of start cell: \n");
            int startingRow = in.nextInt();
            System.out.print("player " + (player + 1) + " please enter column of start cell: \n");
            int startingColumn = in.nextInt();
            System.out.print("player " + (player + 1) + " please enter row of end cell: \n");
            int endingRow = in.nextInt();
            System.out.print("player " + (player + 1) + " please enter column of end cell: \n");
            int endingColumn = in.nextInt();
            --startingRow;
            --startingColumn;
            --endingRow;
            --endingColumn;
            if (startingRow > -1 && startingRow < 8 && startingColumn > -1 && startingColumn < 8 && endingRow > -1 && endingRow < 8 && endingColumn > -1 && endingColumn < 8 && board[endingRow][endingColumn] == ' ') {
                label131: {
                    if (player == 0 && board[startingRow][startingColumn] == 'a') {
                        if (endingRow != startingRow + 1 || endingColumn != startingColumn - 1 && endingColumn != startingColumn + 1) {
                            break label131;
                        }

                        board[endingRow][endingColumn] = 'a';
                        board[startingRow][startingColumn] = ' ';
                        if (player == 0 && endingRow == 7 && board[endingRow][endingColumn] == 'a') {
                            board[endingRow][endingColumn] = 'A';
                        }
                    } else if (player == 1 && board[startingRow][startingColumn] == 'b') {
                        if (endingRow != startingRow - 1 || endingColumn != startingColumn - 1 && endingColumn != startingColumn + 1) {
                            break label131;
                        }

                        board[endingRow][endingColumn] = 'b';
                        board[startingRow][startingColumn] = ' ';
                        if (player == 1 && endingRow == 0 && board[endingRow][endingColumn] == 'b') {
                            board[endingRow][endingColumn] = 'B';
                        }
                    } else if (player == 0 && board[startingRow][startingColumn] == 'A') {
                        if (endingRow != startingRow - 1 && endingRow != startingRow + 1 || endingColumn != startingColumn - 1 && endingColumn != startingColumn + 1) {
                            break label131;
                        }

                        board[endingRow][endingColumn] = 'A';
                        board[startingRow][startingColumn] = ' ';
                    } else {
                        if (player != 1 || board[startingRow][startingColumn] != 'B' || endingRow != startingRow - 1 && endingRow != startingRow + 1 || endingColumn != startingColumn - 1 && endingColumn != startingColumn + 1) {
                            break label131;
                        }

                        board[endingRow][endingColumn] = 'B';
                        board[startingRow][startingColumn] = ' ';
                    }

                    return;
                }
            }

            System.out.println("Invalid path");
        }
    }

    private static boolean checkWinCondition() {
        int cntA = 0;
        int cntB = 0;

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if (board[i][j] == 'a' || board[i][j] == 'A') {
                    ++cntA;
                }

                if (board[i][j] == 'b' || board[i][j] == 'B') {
                    ++cntB;
                }
            }
        }

        if (cntA == 0) {
            System.out.println("Player 2 wins!");
            return true;
        } else if (cntB == 0) {
            System.out.println("Player 1 wins!");
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Checkers game = Checkers.getInstance();
        game.printBoard();
        int player = 0;
        Scanner in = new Scanner(System.in);

        do {
            player = player == 0 ? 1 : 0;
            processPlayerTurn(player, in);
        } while(!checkWinCondition());

        in.close();
    }
}

class Cell {
    private int row;
    private int col;

    Cell(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

class Path {
    private Cell start;
    private Cell end;

    Path(Cell s, Cell e) {
        this.start = s;
        this.end = e;
    }

    public Cell getStart() {
        return this.start;
    }

    public Cell getEnd() {
        return this.end;
    }

    public void setStart(Cell start) {
        this.start = start;
    }

    public void setEnd(Cell end) {
        this.end = end;
    }
}

interface CellFactory {
    Cell createCell(int row, int col);
}

class ConcreteCellFactory implements CellFactory {
    @Override
    public Cell createCell(int row, int col) {
        return new Cell(row, col);
    }
}