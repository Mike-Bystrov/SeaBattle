
import java.io.PrintStream;

public class newDrawer {
    private static final String buf0 = "                                 ";
    private static final String buf1 = "   ";
    private static final String buf2 = "  ";
    public static void draw(PrintStream out, newPlayer.Field aField) {
        char[][] field = aField.getCharField();
        boolean accessForLooking = aField.getAccess();

        char i = 'a';
        out.print("   ");
        for (int j = 0; j < 10; j++) {
             out.print(j+1 + "   ");
        }
        out.println();
        for (int j = 1; j < 11; j++) {
             out.print(i  + "  ");
            for (int k = 1; k<11; k++) {

                if (field[j][k] == '0' && accessForLooking) {
                     out.print(SystemColors.GREEN + field[j][k] + SystemColors.RESET+ "   ");
                } else if (field[j][k] == 'V') {
                     out.print(SystemColors.YELLOW + field[j][k] + SystemColors.RESET+ "   ");
                } else if (field[j][k] == 'X') {
                     out.print(SystemColors.RED + field[j][k] + SystemColors.RESET+ "   ");
                } else {
                     out.print(SystemColors.BLACK + '*' + SystemColors.RESET+ "   ");
                }
            }
            i++;
             out.println();
        }

    }
    public static void draw(PrintStream out, newPlayer player0, newPlayer player1) {

        char[][] field1 = player0.getThisField().getCharField();
        char[][] field2 = player1.getThisField().getCharField();

        boolean b1 = player0.getAccess();
        boolean b2 = player1.getAccess();



        char letter = 'a';
        System.out.print(buf1);
        for (int i = 0; i < 10; i++) {
            System.out.print(i+1 + buf1);
        }
        System.out.print(buf0+"  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i+1 + buf1);
        }
        System.out.println();

        for (int i = 1; i < 11; i++) {
            System.out.print(letter  + "  ");
            for (int j = 1; j<11; j++) {
                if (field1[i][j] == '0' && b1) {
                    System.out.print(SystemColors.GREEN + field1[i][j] + SystemColors.RESET+ "   ");
                } else if (field1[i][j] == 'V') {
                    System.out.print(SystemColors.YELLOW + field1[i][j] + SystemColors.RESET+ "   ");
                } else if (field1[i][j] == 'X') {
                    System.out.print(SystemColors.RED + field1[i][j] + SystemColors.RESET+ "   ");
                } else if (field1[i][j] == 'x') {
                    System.out.print(SystemColors.WHITE + field1[i][j] + SystemColors.RESET+ "   ");
                } else {
                    System.out.print(SystemColors.BLACK + '*' + SystemColors.RESET+ "   ");
                }
            }

            System.out.print(buf0);
            System.out.print(letter  + "  ");
            for (int j = 1; j<11; j++) {
                if (field2[i][j] == '0' && b2) {
                    System.out.print(SystemColors.GREEN + field2[i][j] + SystemColors.RESET+ "   ");
                } else if (field2[i][j] == 'V') {
                    System.out.print(SystemColors.YELLOW + field2[i][j] + SystemColors.RESET+ "   ");
                } else if (field2[i][j] == 'X') {
                    System.out.print(SystemColors.RED + field2[i][j] + SystemColors.RESET+ "   ");
                } else if (field2[i][j] == 'x') {
                    System.out.print(SystemColors.WHITE + field2[i][j] + SystemColors.RESET+ "   ");
                } else {
                    System.out.print(SystemColors.BLACK + '*' + SystemColors.RESET+ "   ");
                }
            }

            letter++;
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }
    public static void draw(PrintStream out, newPlayer.Field aField1, newPlayer.Field aField2) {
        char[][] field1 = aField1.getCharField();
        char[][] field2 = aField2.getCharField();

        boolean b1 = aField1.getAccess();
        boolean b2 = aField2.getAccess();

        String f1 = "field for player";
        String f2 = "field for player";

        char letter = 'a';
        System.out.print(buf1);
        for (int i = 0; i < 10; i++) {
            System.out.print(i+1 + buf1);
        }
        System.out.print(buf0+"  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i+1 + buf1);
        }
        System.out.println();

        for (int i = 1; i < 11; i++) {
            System.out.print(letter  + "  ");
            for (int j = 1; j<11; j++) {
                if (field1[i][j] == '0' && b1) {
                    System.out.print(SystemColors.GREEN + field1[i][j] + SystemColors.RESET+ "   ");
                } else if (field1[i][j] == 'V') {
                    System.out.print(SystemColors.YELLOW + field1[i][j] + SystemColors.RESET+ "   ");
                } else if (field1[i][j] == 'X') {
                    System.out.print(SystemColors.RED + field1[i][j] + SystemColors.RESET+ "   ");
                } else if (field1[i][j] == 'x') {
                    System.out.print(SystemColors.WHITE + field1[i][j] + SystemColors.RESET+ "   ");
                } else {
                    System.out.print(SystemColors.BLACK + '*' + SystemColors.RESET+ "   ");
                }
            }

            System.out.print(buf0);
            System.out.print(letter  + "  ");
            for (int j = 1; j<11; j++) {
                if (field2[i][j] == '0' && b2) {
                    System.out.print(SystemColors.GREEN + field2[i][j] + SystemColors.RESET+ "   ");
                } else if (field2[i][j] == 'V') {
                    System.out.print(SystemColors.YELLOW + field2[i][j] + SystemColors.RESET+ "   ");
                } else if (field2[i][j] == 'X') {
                    System.out.print(SystemColors.RED + field2[i][j] + SystemColors.RESET+ "   ");
                } else if (field2[i][j] == 'x') {
                    System.out.print(SystemColors.WHITE + field2[i][j] + SystemColors.RESET+ "   ");
                } else {
                    System.out.print(SystemColors.BLACK + '*' + SystemColors.RESET+ "   ");
                }
            }

            letter++;
            System.out.println();
        }


    }
}
