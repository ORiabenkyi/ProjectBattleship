import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static final int sizeBoard = 10;
    public static final int numberShips = 5;
    public Scanner incomeScaner = new Scanner(System.in);
    public static Ship[] listShip;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game game;
        game = new Game();
        game.start();
    }

enum typeBoard { OWN, OPONENT}
class Game {
    Player p1;
    Player p2;
        Game() {
            listShip = new Ship[numberShips];
            listShip[0] = new Ship("Aircraft Carrier",5);
            listShip[1] = new Ship("Battleship",4);
            listShip[2] = new Ship("Submarine",3);
            listShip[3] = new Ship("Cruiser",3);
            listShip[4] = new Ship("Destroyer",2);
            p1 = new Player("Player 1");
            p2 = new Player("Player 2");
           //p1 = new ConsolePlayer();
           //p2 = new MailPlayer();
            //shipsBoard = new board(p1);
            //shotsBoard = new board(p1);
        }
        public void start() {

            p1.fillBoard();
            p2.fillBoard();
            Player player, opponent;
            boolean finished = false;
            while(!finished) {
                player.printboard();
                player = player == p1 ? p2 : p1;
                opponent = player == p1 ? p2 : p1;
                String shotResult = player.shoting(opponent);
                System.out.println(shotResult);

                finished = opponent.isAllShipsSunk();
            }
        }
}
class BoardCell {
    protected String name;
    protected char cellDisplay;
    protected String color;
    protected Ship ship;
    protected int positionHorizontal, positionVertical;

    public Ship getShip() {
        return ship;
    }

    public BoardCell(String name) {
        this.name = name;
    }

    public BoardCell(String name, char cellDisplay) {
        this.name = name;
        this.cellDisplay = cellDisplay;
    }

    public BoardCell(String name, char cellDisplay, String color, Ship ship) {
        this.name = name;
        this.cellDisplay = cellDisplay;
        this.color = color;
        this.ship = ship;
    }
}

class Board {
    protected Player player;
    protected BoardCell[][] board;

    public Board(Player player) {
        this.player = player;
        this.initializeBoard();
    }

    protected void initializeBoard() {
        board = new BoardCell[sizeBoard][sizeBoard];
        for (int i = 0; i <sizeBoard ; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                //board[i][j] = new boardCell();

            }
            ;
        }
    }
    public boolean isEmpty(int x, int y){}
    public boolean placeShip(Ship ship, String start, String end) {
        boolean resultRun = true;
        return resultRun;
    }
    public boolean checkLine(String start, String end) {
        boolean resultRun = true;
        return resultRun;
    }
}

class Player {
    protected Board shipsBoardPlayer,ShipsBoardOponent;
    protected String name;

    ArrayList<String> leftShip;
    protected int numberStep;
    protected Step[] listStep;
    public Board getShipsBoardPlayer() {
        return shipsBoardPlayer;
    }

    public Player(String name) {
        this.name = name;
    }

    public void makeBoardPlayer() {
        this.shipsBoardPlayer  = new Board(this);
        this.ShipsBoardOponent = new Board(this);
    }
    public String requestCoordinates() {
        System.out.println("Enter the coordinates");
        String income = incomeScaner.nextLine();
        return income;
    }
    public String requestCoordinates(Ship ship) {
        System.out.printf("Enter the coordinates of %s (%d cells):\n",ship.getName(),ship.getLength());
        String income = incomeScaner.nextLine();
        String[] coordinatesArray = income.split(" ");
        for (String eachNumber:coordinatesArray) {
            String numberPart = eachNumber.replaceAll("([a-zA-Z])", "");
            String leterPart;
            if (Integer.parseInt(numberPart) <= sizeBoard && Integer.parseInt(numberPart) >0 ) { //all good
                ; }else {
                ;
            }

        }
        return income;
    }
    public String shoting(Player oponent) {
        String resultShoting = "";
        return resultShoting;
    }
    public boolean isAllShipsSunk() {
        boolean result = false;
        return result;
    }
    public void printboard() {
    }
    public void fillBoard() {
        for (Ship eachShip :
                listShip) {
            getShipsBoardPlayer().placeShip(eachShip, requestCoordinates(eachShip),"");
            leftShip.add(eachShip.getName());
        }
    }
}
class Step {
        protected String name;
        protected boolean hit;
    public Step(String name) {
        this.name = name;
    }

    public boolean isHit() {
        return hit;
    }
}

class Ship {
    protected String name;
    protected int length;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}

}