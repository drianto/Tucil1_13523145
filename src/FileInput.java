import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput {
    public static File userFile(Scanner input) {
        
        System.out.print("Masukkan nama file: ");
        String fileName = input.nextLine();
        
        File file = new File("input/" + fileName);

        
        return file;
    }

    public static void printFile(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
    }

    public static String[] readFile(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            String[] lines = new String[10000];
            int count = 0;

            while (fileScanner.hasNextLine() && count < lines.length) {
                lines[count++] = fileScanner.nextLine();
            }
            fileScanner.close();

            String[] result = new String[count];
            System.arraycopy(lines, 0, result, 0, count);
            return result;

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan." );
            return new String[0];
        }
    }
}
