import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileOutput {
    public static final Scanner inputFile = new Scanner(System.in);
    public static char[][] board;

    public static Path fileName(String prompt) {
        System.out.print(prompt);
        String fileName = inputFile.nextLine().trim();

        Path path = Paths.get("test", fileName);

        return path;
    }

    public static void saveSolutionFile(String[] lines) {
        Path filePath = fileName("Masukkan nama file (contoh: solusi.txt): ");

        try (PrintWriter out = new PrintWriter(new FileWriter(filePath.toFile()))) {
            out.println("Solusi ditemukan!");
            out.println();
            char[][] board = Grid.board;
            for (char[] row : board) {
                out.println(new String(row));
            }
            out.println();
            out.println("Estimasi waktu penyelesaian: " + Solver.timeEstimated + "ms");
            out.println();
            out.println("Banyak percobaan: " + Solver.noOfAttempt);
            System.out.println("Solusi berhasil disimpan di " + filePath);
        } catch (IOException e) {
            System.err.printf("Error saat menyimpan file: %s%n", e.getMessage());
        }
    }

    public static void savingSolution(String[] lines) {
        System.out.print("Apakah ingin menyimpan solusi (ya/tidak)? ");
        if (inputFile.hasNextLine()) { 
            String ans = inputFile.nextLine().trim();
            if (ans.equalsIgnoreCase("ya")) {
                saveSolutionFile(lines);
            }
        } else {
            System.out.println("Tidak ada input yang tersedia, solusi tidak disimpan.");
        }
    }
    
}
