import java.util.Deque;
import java.util.ArrayDeque;

public class Solver {
    public static Deque <char[][][]> shapeList = new ArrayDeque<>();
    public static double timeEstimated = 0;
    public static long noOfAttempt = 0;

    public static char[][] rotate(char[][] shape) {
        int row = shape.length;
        int column = shape[0].length;
        char[][] newShape = new char[column][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newShape[j][row - 1 - i] = shape[i][j];
            }
        }

        return newShape;
    }

    public static char[][] mirror(char[][] shape) {
        int row = shape.length;
        int column = shape[0].length;
        char[][] newShape = new char[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newShape[i][j] = shape[i][column - 1 - j];
            }
        }

        return newShape;
    }

    public static char[][][] shapeTransform(char[][] shape) {
        char[][][] newShape = new char[8][][];
        //rotate
        newShape[0] = shape;          
        newShape[1] = rotate(newShape[0]); 
        newShape[2] = rotate(newShape[1]); 
        newShape[3] = rotate(newShape[2]);  
        // mirror
        newShape[4] = mirror(newShape[0]);  
        newShape[5] = rotate(newShape[4]); 
        newShape[6] = rotate(newShape[5]);  
        newShape[7] = rotate(newShape[6]);  
        
        return newShape;
    }

    public static void dataTransform() {
        for (char[][] piece: Piece.pieceList) {
            shapeList.offer(shapeTransform(piece));
        }
    }

    public static boolean finishBoard() {
        for (char[] row : Grid.board) {
            for (char column : row) {
                if (column == '0'){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean processSolve() {
        if(shapeList.isEmpty()) {
            if(finishBoard()) {
                return true;
            } else {
                return false;
            }
        }

        char[][][] shape = shapeList.pop();

        for (int i = 0; i < Grid.board.length; i++) {
            for (int j = 0; j < Grid.board[0].length; j++) {
                for (char[][] piece : shape) {
                    if (canPlacePiece(piece, i, j)) {
                        placePiece(piece, i, j);
                        noOfAttempt++;
                        if(processSolve()) {
                            return true;
                        } 
                        removePiece(piece, i, j);
                    }
                }
            }
        }

        shapeList.push(shape);
        return false;
    }

    public static boolean canPlacePiece(char[][] piece, int x, int y) {
        int pieceRow = piece.length;
        int pieceColumn = piece[0].length;
        int boardRow = Grid.board.length;
        int boardColumn = Grid.board[0].length;
    
        for (int i = 0; i < pieceRow; i++) {
            for (int j = 0; j < pieceColumn; j++) {
                if (piece[i][j] != '0') {
                    int boardX = x + i;
                    int boardY = y + j;
    
                    if (boardX >= boardRow || boardY >= boardColumn) {
                        return false;
                    }
    
                    if (Grid.board[boardX][boardY] != '0') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void placePiece(char[][] piece, int x, int y) {
        int pieceRow = piece.length;
        int pieceColumn = piece[0].length;
    
        for (int i = 0; i < pieceRow; i++) {
            for (int j = 0; j < pieceColumn; j++) {
                if (piece[i][j] != '0') {
                    int boardX = x + i;
                    int boardY = y + j;
                    Grid.board[boardX][boardY] = piece[i][j];
                }
            }
        }
    }

    public static void removePiece(char[][] piece, int x, int y) {
        int pieceRow = piece.length;
        int pieceColumn = piece[0].length;
    
        for (int i = 0; i < pieceRow; i++) {
            for (int j = 0; j < pieceColumn; j++) {
                if (piece[i][j] != '0') {
                    int boardX = x + i;
                    int boardY = y + j;
                    Grid.board[boardX][boardY] = '0';
                }
            }
        }
    }

    public static void timeElapsed() {
        long start = System.nanoTime();
        processSolve();
        long finish = System.nanoTime();
        timeEstimated = (double)(finish - start) / 1e+6;
    }
}
