public class HandleInput {
    public static void checkInput(String[] lines) {
        if (lines == null || lines.length < 3) {
            System.out.println("Format tidak sesuai, file input kurang dari 3 baris");
            System.exit(1);
        }

        String[] firstLine = lines[0].trim().split("\\s+");
        if (firstLine.length != 3) {
            System.out.println("Format tidak sesuai, baris pertama harus memiliki 3 input angka");
            System.exit(1);
        }

        if (!lines[1].strip().equals("DEFAULT")) {
            System.out.println("Format bentuk tidak sesuai, hanya bisa menerima format DEFAULT");
            System.exit(1);
        }              

        int N = 0;
        int M = 0;
        int P = 0;
        try {
            N = Integer.parseInt(firstLine[0]);
            M = Integer.parseInt(firstLine[1]);
            P = Integer.parseInt(firstLine[2]);

            if (N <= 0 || M <= 0 || P <= 0) {
                System.out.println("Format tidak sesuai");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Format tidak sesuai");
            System.exit(1);
        }

        if (lines[1].trim().isEmpty()) {
            System.out.println("Format tidak sesuai");
            System.exit(1);
        }

        if (lines.length < P + 2) {
            System.out.println("Format tidak sesuai");
            System.exit(1);
        }
    }
}
