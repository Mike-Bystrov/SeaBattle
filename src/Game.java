
public class Game {
    private static newPlayer player0;
    private static newPlayer player1;
    public static void start() {
        player0 = new newPlayer(PlayerType.computer);
        player1 = new newPlayer(PlayerType.computer);
        newDrawer.draw(System.out, player0.getThisField(), player1.getThisField());

        player0.setAccessForLooking(true);
        int num = 0;
        while (true) {
            player0.act(player1);
            changeAccess();
            if(player0.isWinner())break;

            player1.act(player0);
            changeAccess();
            if(player1.isWinner())break;
            num++;
        }
        if (player0.isWinner()) {
            System.out.println(player0);
            System.out.println("winner!");
        } else if (player1.isWinner()) {
            System.out.println(player1);
            System.out.println("winner!");
        }
        System.out.println("кол-во ходов: " + num);
    }
    private static void changeAccess() {
        player0.setAccessForLooking(!player0.getAccess());
        player1.setAccessForLooking(!player1.getAccess());
    }

}
