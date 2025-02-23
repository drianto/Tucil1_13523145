import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File file = FileInput.userFile(input);
    
        String[] lines = FileInput.readFile(file);
        HandleInput.checkInput(lines);

        Grid.Board(lines);
        Piece.processPieces(lines);

        Solver.dataTransform();

        Solver.timeElapsed();
        System.out.println();
        boolean result = Solver.processSolve();
        if(!result) {
            System.out.println("Tidak ada solusi");
        } else {
            System.out.println("Solusi ditemukan!");
            System.out.println();
            Grid.printBoard(Grid.board);
        }
        System.out.println();
        System.out.println("Estimasi waktu penyelesaian: " + Solver.timeEstimated + "ms");
        System.out.println();
        System.out.println("Banyak percobaan: " + Solver.noOfAttempt);
        System.out.println();

        if (result) {
            FileOutput.savingSolution(lines);
        }

        input.close();
    }
}
