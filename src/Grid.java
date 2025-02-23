public class Grid {
    public static char[][] board;

    public static void Board(String[] lines) {
        String[] data = lines[0].split(" ");
        int row = Integer.parseInt(data[0]);
        int column = Integer.parseInt(data[1]);

        board = new char[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = '0';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(boardColoring(board[i][j]));
            }
            System.out.println();
        }
    }
    
    public static String boardColoring(char character) {
        String init = "\u001B[0m";
        String[] color = {
            "\u001B[31m", 
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m",
            "\u001B[35m",
            "\u001B[36m",
            "\u001B[91m",
            "\u001B[92m",
            "\u001B[93m",
            "\u001B[94m",
            "\u001B[95m",
            "\u001B[96m",
        };
    
        if (character >= 'A' && character <= 'Z') {
            int index = (character - 'A') % color.length;
            return color[index] + character + init;
        }
    
        return "\u001B[37m" + character + init;
    }
    
}
