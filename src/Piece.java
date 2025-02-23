import java.util.List;
import java.util.ArrayList;

public class Piece {
    public static List <char[][]> pieceList = new ArrayList<>();

    public static int getPieceCount(String[] lines) {
        String[] data = lines[0].split(" ");
        return Integer.parseInt(data[2]);
    }

    public static void printPieceCount(int pieceCount) {
        System.out.println(pieceCount);
    }

    public static char[][] createPieceShape(String[] pieceLines) {
        int row = pieceLines.length;
        if (row == 0) {
            return null;  
        } 

        int maxLength = 0;
        for (String line : pieceLines) {
            maxLength = Math.max(maxLength, line.trim().length());
        }

        char[][] shape = new char[row][maxLength];
        for (int i = 0; i < row; i++) {
            char[] rowChars = pieceLines[i].trim().toCharArray();
            int column = rowChars.length;

            for (int j = 0; j < maxLength; j++) {
                if (j < column) {
                    shape[i][j] = rowChars[j];
                } else {
                    shape[i][j] = '0';
                }
            }
        }

        return shape;
    }

    public static void printPiece(char[][] shape) {
        for (char[] row : shape) {
            System.out.println(new String(row));
        }
    }

    public static void processPieces(String[] lines) {
        int index = 2;
        while (index < lines.length) {
            String first = lines[index].trim();
            if (first.isEmpty()) {
                index++;
                continue;
            }

            char letter = first.charAt(0);
            int pieceStart = index;

            while (index < lines.length && lines[index].trim().charAt(0) == letter) {
                index++;
            }

            int pieceSize = index - pieceStart;
            String[] pieceLines = new String[pieceSize];
            System.arraycopy(lines, pieceStart, pieceLines, 0, pieceSize);

            char[][] shape = createPieceShape(pieceLines);
            pieceList.add(shape);
        }
    }
}
