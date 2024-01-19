import java.util.Scanner;
import java.util.ArrayList;

public class Checkers {
    private static final int BOARD_SIZE = 8;
    private static final int NUM_INITIAL_ROWS = 3;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_A_PIECE = 'a';
    private static final char PLAYER_B_PIECE = 'b';
    private static final char PLAYER_A_KING = 'A';
    private static final char PLAYER_B_KING = 'B';
    private static char board[][] = new char [BOARD_SIZE][BOARD_SIZE];
    private static Checkers instance; // Singleton instance

    private static CellFactory cellFactory = new ConcreteCellFactory();

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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (i%2 == 0) {
                for (int j = 1; j < 8; j += 2) {
                    board[i][j] = PLAYER_A_PIECE;
                }
            } else {
                for (int j=0 ; j<8 ; j+=2) {
                    board[i][j] = PLAYER_A_PIECE;
                }
            }
        }
        for (int i = 7; i > 4; i--) {
            if (i%2 == 0) {
                for (int j = 1; j < 8; j += 2) {
                    board[i][j] = PLAYER_B_PIECE;
                }
            } else {
                for (int j = 0; j < 8; j += 2) {
                    board[i][j] = PLAYER_B_PIECE;
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 1; i < 9; i++) {
            System.out.print("   " + i);
        }

        System.out.print("\n  ");

        for (int i = 0; i < 8; i++) {
            System.out.print("+---");
        }

        System.out.print("+\n") ;

        for (int i = 0; i < 8; i++) {
            System.out.print((i+1) + " ");

            for (int j = 0; j < 8; j++) {
                System.out.print("| " + board[i][j] + " ");
            }

            System.out.print("|\n  ");

            for (int i2 = 0; i2 < 8; i2++) {
                System.out.print("+---");
            }

            System.out.print("+\n") ;
        }
    }

    private static void processPlayerTurn(int player, Scanner in) {
        int x = 0, once = 1, row1 = 0, column1 = 0;

        while (x == 0) {
            ArrayList<Path> cellsGo = new ArrayList<>();
            x = 1;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (once==0) {
                        i = row1;
                        j = column1;
                    }
                    Cell now = cellFactory.createCell(i, j); // use factory to create cells
                    if (player == 0 && board[i][j] == 'a') {
                        if (i < 6 && j > 1 && (board[i+1][j-1] == 'b' || board[i+1][j-1] == 'B') && board[i+2][j-2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i+2 , j-2)));
                            x=0;
                        }
                        if (i < 6 && j < 6 && (board[i+1][j+1] == 'b' || board[i+1][j+1] == 'B') && board[i+2][j+2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i+2 , j+2)));
                            x=0;
                        }
                    } else if (player == 1 && board[i][j] == 'b') {
                        if (i > 1 && j > 1 && (board[i-1][j-1] == 'a' || board[i-1][j-1] == 'A') && board[i-2][j-2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i-2 , j-2)));
                            x=0;
                        }
                        if (i > 1 && j < 6 && (board[i-1][j+1] == 'a' || board[i-1][j+1] == 'A') && board[i-2][j+2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i-2 , j+2)));
                            x=0;
                        }
                    } else if (player==0 && board[i][j] == 'A') {
                        if (i > 1 && j > 1 && (board[i-1][j-1] == 'b' || board[i-1][j-1] == 'B') && board[i-2][j-2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i-2 , j-2)));
                            x=0;
                        }
                        if (i > 1 && j < 6 && (board[i-1][j+1] == 'b' || board[i-1][j+1] == 'B') && board[i-2][j+2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i-2 , j+2)));
                            x=0;
                        }
                        if (i < 6 && j > 1 && (board[i+1][j-1] == 'b' || board[i+1][j-1] == 'B') && board[i+2][j-2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i+2 , j-2)));
                            x=0;
                        }
                        if (i < 6 && j < 6 && (board[i+1][j+1] == 'b' || board[i+1][j+1] == 'B') && board[i+2][j+2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i+2 , j+2)));
                            x=0;
                        }
                    } else if (player == 1 && board[i][j] == 'B') {
                        if (i > 1 && j > 1 && (board[i-1][j-1] == 'a' || board[i-1][j-1] == 'A') && board[i-2][j-2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i-2 , j-2)));
                            x=0;
                        }
                        if (i > 1 && j < 6 && (board[i-1][j+1] == 'a' || board[i-1][j+1] == 'A') && board[i-2][j+2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i-2 , j+2)));
                            x=0;
                        }
                        if (i < 6 && j > 1 && (board[i+1][j-1] == 'a' || board[i+1][j-1] == 'A') && board[i+2][j-2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i+2 , j-2)));
                            x=0;
                        }
                        if (i < 6 && j < 6 && (board[i+1][j+1] == 'a' || board[i+1][j+1] == 'A') && board[i+2][j+2] == ' ') {
                            cellsGo.add(new Path(now, new Cell(i+2 , j+2)));
                            x=0;
                        }
                    }
                    if (once == 0) {
                        break;
                    }
                } // end j loop
                if (once == 0){
                    break ;
                }
            } // end i loop

            if (x == 1) {
                break;
            } else {
                capturePiece(player, cellsGo, in);
                once = 0;
            }
            printBoard();
        }

        if (once == 1) {
            movePiece(player, in);
            printBoard();
        }
    }

    private static void capturePiece(int player, ArrayList<Path> cellsGo, Scanner in) {
        System.out.print("player " + (player + 1) + " please choose one of the cell(s) that show below for next move : \n");
        int y = 1;
        for (Path p : cellsGo) {
            CellDecorator highlightedCell = new HighlightedCellDecorator(p.getEnd());
            highlightedCell.decorate(); // This will now correctly call the decorate method
        }
        int tmp = in.nextInt();
        int row1 = cellsGo.get(tmp - 1).getEnd().getRow();
        int column1 = cellsGo.get(tmp - 1).getEnd().getColumn();
        board[row1][column1] = board[cellsGo.get(tmp - 1).getStart().getRow()][cellsGo.get(tmp - 1).getStart().getColumn()];
        board[cellsGo.get(tmp - 1).getStart().getRow()][cellsGo.get(tmp - 1).getStart().getColumn()] = EMPTY_CELL;
        int newRow = (cellsGo.get(tmp - 1).getStart().getRow() + row1) / 2;
        int newCol = (cellsGo.get(tmp - 1).getStart().getColumn() + column1) / 2;
        if (player == 0 && row1 == 7 && board[row1][column1] == PLAYER_A_PIECE) board[row1][column1] = PLAYER_A_KING;
        if (player == 1 && row1 == 0 && board[row1][column1] == PLAYER_B_PIECE) board[row1][column1] = PLAYER_B_KING;
        board[newRow][newCol] = EMPTY_CELL;
    }

    private static void movePiece(int player, Scanner in) {
        do {
            System.out.print("player " + (player+1) + " please enter row of start cell: \n");
            int startingRow = in.nextInt() ;
            System.out.print("player " + (player+1) + " please enter column of start cell: \n");
            int startingColumn = in.nextInt();
            System.out.print("player " + (player+1) + " please enter row of end cell: \n");
            int endingRow = in.nextInt();
            System.out.print("player " + (player+1) + " please enter column of end cell: \n");
            int endingColumn = in.nextInt();
            startingRow--;
            startingColumn--;
            endingRow--;
            endingColumn--;

            if (startingRow>-1 && startingRow<8 && startingColumn>-1 && startingColumn<8 && endingRow>-1 && endingRow<8 && endingColumn>-1 && endingColumn<8 && board[endingRow][endingColumn]==' ') {
                if (player == 0 && board[startingRow][startingColumn] == 'a') {
                    if (endingRow == startingRow+1 && (endingColumn == startingColumn-1 || endingColumn == startingColumn+1)) {
                        board[endingRow][endingColumn] = 'a';
                        board[startingRow][startingColumn] = ' ';
                        if (player==0 && endingRow==7 && board[endingRow][endingColumn]=='a') board[endingRow][endingColumn] = 'A';
                        break;
                    }
                } else if (player == 1 && board[startingRow][startingColumn] == 'b') {
                    if ( endingRow == startingRow-1 && (endingColumn == startingColumn-1 || endingColumn == startingColumn+1)) {
                        board[endingRow][endingColumn] = 'b';
                        board[startingRow][startingColumn] = ' ';
                        if (player == 1 && endingRow==0 && board[endingRow][endingColumn] == 'b') board[endingRow][endingColumn] = 'B';
                        break;
                    }
                } else if (player == 0 && board[startingRow][startingColumn] == 'A') {
                    if ( (endingRow==startingRow-1 || endingRow == startingRow+1) && (endingColumn==startingColumn-1 || endingColumn == startingColumn+1)) {
                        board[endingRow][endingColumn] = 'A';
                        board[startingRow][startingColumn] = ' ';
                        break;
                    }
                } else if (player == 1 && board[startingRow][startingColumn] == 'B') {
                    if ( (endingRow == startingRow-1 || endingRow == startingRow+1) && (endingColumn == startingColumn-1 || endingColumn == startingColumn+1)) {
                        board[endingRow][endingColumn] = 'B';
                        board[startingRow][startingColumn] = ' ';
                        break;
                    }
                }
            }
            System.out.println("Invalid path");
        } while(true);
    }

    private static boolean checkWinCondition() {
        int cntA = 0, cntB = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == PLAYER_A_PIECE || board[i][j] == PLAYER_A_KING) cntA++;
                if (board[i][j] == PLAYER_B_PIECE || board[i][j] == PLAYER_B_KING) cntB++;
            }
        }
        if (cntA == 0) {
            System.out.println("Player 2 wins!");
            return true;
        }
        if (cntB == 0) {
            System.out.println("Player 1 wins!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Checkers game = Checkers.getInstance();
        game.printBoard();
        int player = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            player = (player == 0) ? 1 : 0;

            processPlayerTurn(player, in);

            if (checkWinCondition()) {
                break;
            }
        }
        in.close();
    }
}

// CellFactory Interface
interface CellFactory {
    Cell createCell(int row, int col);
}

// ConcreteCellFactory Class
class ConcreteCellFactory implements CellFactory {
    @Override
    public Cell createCell(int row, int col) {
        return new Cell(row, col);
    }
}

class Cell {
    private int row, col;

    Cell (int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

abstract class CellDecorator extends Cell {
    protected Cell decoratedCell;

    public CellDecorator(Cell decoratedCell) {
        super(decoratedCell.getRow(), decoratedCell.getColumn());
        this.decoratedCell = decoratedCell;
    }

    public abstract void decorate();
}

class HighlightedCellDecorator extends CellDecorator {
    public HighlightedCellDecorator(Cell decoratedCell) {
        super(decoratedCell);
    }

    @Override
    public void decorate() {
        // Add highlight functionality
        System.out.println("Cell at [" + getRow() + ", " + getColumn() + "] is highlighted.");
        // You can add more functionality here, like changing the cell's appearance in the UI if you have one.
    }
}


class Path {
    private Cell start, end;

    Path (Cell s, Cell e) {
        this.start = s;
        this.end = e;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    public void setStart(Cell start) {
        this.start = start;
    }

    public void setEnd(Cell end) {
        this.end = end;
    }
}