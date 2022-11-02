import java.util.Random;
import java.util.Scanner;

// это новое видение класса player
// теперь не будет классов отдельно для компьютера и человека
// вместо этого будет enum для типа игрока
// то же самое планирую сделать и для поля
// хотя с полем ситуация немного сложнее
// з.ы. а может и нет =)
//
//
//
//
public class newPlayer {
    private static PlayerType type;
    private String name;
    private static int playersNumber = 0;
    private final Field thisField;
    private int defeatedBlocks;
    private static final Random random = new Random();
    private boolean accessForLooking = false;
    private static final Scanner scanner = new Scanner(System.in);
    private boolean winner = false;

    public boolean isWinner() {
        winner = true;
        return defeatedBlocks == 20;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setType(PlayerType aType) {
        type = aType;
    }
    public String getType() {
        return type.name();
    }

    public Field getThisField() {
        return thisField;
    }

    public newPlayer(PlayerType aType) {
        defeatedBlocks = 0;
        type = aType;
        name = "player" + playersNumber;
        playersNumber++;
        thisField = new Field();
        thisField.initField();
    }

    public void setAccessForLooking(boolean access) {
        accessForLooking = access;
    }
    public boolean getAccess() {return accessForLooking;}

    public void act(newPlayer enemy) {
        char[][]field = enemy.getThisField().field;
        boolean isComp = type == PlayerType.computer;
        int x, y;
        if (!isComp) {
            System.out.println("Выберите, куда стрелять:\n");
            x = scanner.next().charAt(0) - 96;
            y = scanner.nextInt();
        } else {
            x = random.nextInt(10)+1;
            y = random.nextInt(10)+1;
        }
        boolean check = false;
        while (isHitted(x,y,enemy.getThisField()) && getDefeatedBlocks() < 20) {
            defeatedBlocks++;
            char tmp = (char) (x + 96);
            if(isComp) System.out.println( tmp + " " + y + "\n");

            if(field[x][y] == 'V') {
                checkDefeatedShip(x,y);
                //drawer.draw();
                newDrawer.draw(System.out, this.thisField, enemy.getThisField());
                check = true;
            }

            if (!isComp) {
                System.out.println("Выберите, куда стрелять:\n");
                x = scanner.next().charAt(0) - 96;
                y = scanner.nextInt();
            } else {
                x = random.nextInt(10)+1;
                y = random.nextInt(10)+1;
            }
        }
        // сделать проверку на подбитие всех кораблей
        //System.out.println("field for" + this.getName());
        if (!check) newDrawer.draw(System.out, this, enemy);
        if(type != PlayerType.computer) System.out.println("Вы промахнулись! Теперь ход соперника");
    }

    public boolean isHitted(int x, int y, Field enField) {
        char[][] plField = enField.getCharField();

        boolean isHitted = false;
        if (x<0 || x>10 || y<0 || y>10) return false;

        char cond = plField[x][y];
        switch (cond) {
            case '*' -> {
                plField[x][y] = 'x';
            }
            case '0' -> {
                plField[x][y] = 'V';
                isHitted = true;
            }
            default -> {
            }
        }
        return isHitted;
    }

    public void checkDefeatedShip(int x, int y) {
        // если ранил, то проверить на потопление корабля
    }

    public int getDefeatedBlocks() {
        return defeatedBlocks;
    }



    @Override
    public String toString() {
        return "тип игрока -- " + type + "\n" +
                "имя игрока -- " + name + "\n";
    }


    public class Field {
        private final char[][] field;
        private static final String[] directions = {"right","down"};

        public Field() {
            field = new char[12][12];

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    field[i][j] = '*';
                }
            }
        }

        public boolean getAccess() {
            return accessForLooking;
        }
        public char[][] getCharField() {
            return field;
        }

        public boolean isShipPositionValid(int xPos, int yPos, int len, String dir){
            int isRight, isDown;
            if (dir.equals("right")) {
                isRight = 1;
                isDown = 0;
            } else {
                isRight = 0;
                isDown = 1;
            }
            // проверка на попадание в игровое поле
            if(xPos < 1 || xPos > 10 || yPos < 1 || yPos > 10) return true;

            // проверка на выход за границы
            if(yPos + len - 1 > 10 || xPos + len - 1 > 10) return true;

            // (x + i*isDown, y + i*isRight)  i = -1, 0, ... , len
            // клетки поля, отвечающие за линию корабля + 2 соседних клетки

            // for i = -1, len
            // (xPos-1*isRight+i*isDown, yPos+i*isRight-1*isDown) -- случай 1
            // (xPos+1*isRight+i*isDown, yPos+i*isRight+1*isDown) -- случай 2

            // проверка соседей
            for (int i=-1; i<len+1; i++) {
                int firstXPos = xPos- isRight +i*isDown;
                int secondXPos = xPos+ isRight +i*isDown;
                int firstYPos = yPos+i*isRight- isDown;
                int secondYPos = yPos+i*isRight+ isDown;
                if (firstXPos > 0 && firstXPos <11 && firstYPos > 0 && firstYPos <11) {
                    if (field[firstXPos][firstYPos] != '*')
                        return true;
                }
                if (secondXPos > 0 && secondXPos <11 && secondYPos > 0 && secondYPos <11) {
                    if (field[secondXPos][secondYPos] != '*')
                        return true;
                }
            }

            if (field[xPos - isDown][yPos - isRight] != '*') return true;
            if (field[xPos +len*isDown][yPos +len*isRight] != '*') return true;

            return false;
        }
        public void putShips(int x, int y, int len, String dir) {
            int isRight = dir.equals("right") ? 1 : 0;
            int isDown = dir.equals("down") ? 1 : 0;

            for (int i = 0; i < len; i++) {
                field[x + i*isDown][y + i*isRight] = '0';
            }
        }
        public void initField() {

            boolean isComp = type == PlayerType.computer;
            int len;
            //первый цикл отвечает за тип кораблей
            //второй -- за кол-во
            //i=0: один   4-палубник
            //i=1: два    3-палубника
            //i=2: три    2-палубника
            //i=3: четыре 1-палубника
            for (int i = 0; i < 4; i++) {
                len = 4-i;
                if(!isComp)System.out.print("\nрасставьте " + len + "-палубный(е) корабль(и)\n");

                String dir; // пока не буду делать enum, так как не вижу в этом смысла
                int inputX;
                int inputY;
                if(!isComp) newDrawer.draw(System.out, this);
                for (int j = 0; j < i+1; j++) {
                    do {
                        if (!isComp) {
                            inputX = scanner.next().charAt(0) - 96;
                            inputY = scanner.nextInt();
                            dir = (i == 3) ? "" : scanner.next();
                        }
                        else {
                            inputX = random.nextInt(11);
                            inputY = random.nextInt(11);

                            int randDir = random.nextInt(2);
                            dir = directions[randDir];
                        }
                        if(isShipPositionValid(inputX, inputY, len, dir) && !isComp) {
                            System.out.println("вы ввели координаты неккоректно, попробуйте ещё раз");
                        }
                    } while(isShipPositionValid(inputX, inputY, len, dir));

                    putShips(inputX, inputY, len, dir);
                    if(!isComp) newDrawer.draw(System.out, this);
                }
            }
            if(isComp) newDrawer.draw(System.out,this);
        }
    }
}
