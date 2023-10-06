import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static final int sizeBoard = 10;
    public static final int numberShips = 5;
    public static Scanner incomeScaner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game game;
        game = new Game();
        game.start();
    }


}
class Game {
    public static Ship[] listShip;
    Player p1;
    Player p2;
    Game() {
        listShip = new Ship[Main.numberShips];
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
        p1.printboard(Board.typeBoard.OWN);
//        p2.fillBoard();
//        Player player = null, opponent = null;
//        boolean finished = false;
        //while(!finished) {
            //player.printboard();
//            player = player == p1 ? p2 : p1;
//            opponent = player == p1 ? p2 : p1;
//            String shotResult = player.shoting(opponent);
//            System.out.println(shotResult);

//            finished = opponent.isAllShipsSunk();
        //}
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
        this.ship = null;
    }

    public void setPosition(int positionHorizontal,int positionVertical) {
        this.positionHorizontal = positionHorizontal;
        this.positionVertical   = positionVertical;
    }

    public int getPositionHorizontal() {
        return positionHorizontal;
    }

    public int getPositionVertical() {
        return positionVertical;
    }

    public BoardCell(String name, char cellDisplay, String color, Ship ship) {
        this.name = name;
        this.cellDisplay = cellDisplay;
        this.color = color;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.setCellDisplay('O');
    }

    public void setCellDisplay(char cellDisplay) {
        this.cellDisplay = cellDisplay;
    }

    public char getCellDisplay() {
        return cellDisplay;
    }

}

class Board {
    public final char firstLeter = 'A';
    public static final String allowedLetters = "abcdefghijABCDEFGHIJ";
    public final char lastLeter  = (char)((int)firstLeter + Main.sizeBoard);
    protected Player player;
    protected BoardCell[][] board;
    enum typeBoard { OWN, OPONENT}

    public Board(Player player) {
        this.player = player;
        this.initializeBoard();
    }
    public BoardCell getCell(int x, int y) {
        return this.board[x][y];
    }

    public BoardCell[][] getBoard() {
        return board;
    }

    protected void initializeBoard() {
        board = new BoardCell[Main.sizeBoard][Main.sizeBoard];
        int count = 0;
        for (char i = firstLeter; i < lastLeter ; i++, count++) {
            for (int j = 0; j < Main.sizeBoard; j++) {
                board[count][j] = new BoardCell("" + i + (j+1),'~');
                board[count][j].setPosition(count,j);
            }
        }
    }
    public boolean isEmpty(int x, int y){ return true;}
//    public boolean placeShip(Ship ship, String start, String end) {
//        boolean resultRun = true;
//        return resultRun;
//    }
    public boolean placeShip(Ship ship, String coordinatString) {
        boolean resultRun = true;
        String[] coordinatesArray = coordinatString.split(" ");
        BoardCell[] listCell;

        String firsCoorfinat = coordinatesArray[0];
        String secondCoordinat = coordinatesArray[1];
        //check that is a line
        //1 line same liters
        //2 line same numbers
        int numberPartFirst = Integer.parseInt(firsCoorfinat.replaceAll("([a-zA-Z])", ""));
        String leterPartFirst  = firsCoorfinat.replaceAll("([0-9])", "");;
        int numberPartSecond = Integer.parseInt(secondCoordinat.replaceAll("([a-zA-Z])", ""));
        String leterPartSecond  = secondCoordinat.replaceAll("([0-9])", "");;
        if (leterPartFirst.length() == 1 && leterPartSecond.length() == 1 && leterPartSecond.equals(leterPartFirst)){
            int count = ship.getLength();
            listCell = new BoardCell[count];
            leterPartFirst = leterPartFirst.toUpperCase();
            char letter = leterPartFirst.charAt(0);
            int coloms = Math.abs(numberPartSecond - numberPartFirst)+1;
            if ( coloms != count) {
                resultRun = false;
                return resultRun;
            }
            int firsPosition = (numberPartFirst>numberPartSecond?numberPartSecond:numberPartFirst);
            //int lastPosition = (numberPartFirst>numberPartSecond?numberPartSecond:numberPartFirst);;
            coloms--;
            int rowNumber = Math.abs('A' - letter);
            for (int i = 0;i < count;i++){
                listCell[i] = this.getCell(rowNumber, (firsPosition+i-1));
            }
            for (BoardCell shipCell:listCell) {
                if (this.checkCellOnShip(shipCell)) {
                    resultRun = false;
                    return resultRun;
                }
                if (this.checkCellAroundOnShips(shipCell)) {
                    resultRun = false;
                    return resultRun;
                }
            }
            for (BoardCell shipCell:listCell) {
                shipCell.setShip(ship);
            }
        } else {
            int count = ship.getLength();
            listCell = new BoardCell[count];
            leterPartFirst  = leterPartFirst.toUpperCase();
            leterPartSecond = leterPartSecond.toUpperCase();
            char charPartFirst = leterPartFirst.charAt(0);
            char charPartSecond = leterPartSecond.charAt(0);
            char letter = (charPartFirst > charPartSecond ?charPartSecond:charPartFirst);
            int  coloms = Math.abs(charPartSecond - charPartFirst)+1;
            if ( coloms != count) {
                resultRun = false;
                return resultRun;
            }
            coloms--;
            int colomsNumber = Math.abs('A' - letter);

            for (int i = 0;i < count;i++){
                listCell[i] = this.getCell(colomsNumber+i, numberPartFirst-1);
            }
            for (BoardCell shipCell:listCell) {
                if (this.checkCellOnShip(shipCell)) {
                    resultRun = false;
                    return resultRun;
                }
                if (this.checkCellAroundOnShips(shipCell)) {
                    resultRun = false;
                    return resultRun;
                }
            }
            for (BoardCell shipCell:listCell) {
                shipCell.setShip(ship);
            }
        }


        return resultRun;
    }
    public boolean checkLine(String start, String end) {
        boolean resultRun = true;
        return resultRun;
    }


    public boolean checkCellOnShip(BoardCell boardCell) {
        boolean resultCheck = true;
        if (boardCell.getShip() == null) {
            resultCheck = false;
            return resultCheck;
        }
        return resultCheck;
    }
    public boolean checkCellAroundOnShips(BoardCell boardCell) {
        boolean resultCheck = false;
        ArrayList<BoardCell> listCellArround = new ArrayList<BoardCell>();
        if (boardCell.getPositionHorizontal() == 0) {
            // need take 0 and 1
            if (boardCell.getPositionVertical() == 0) {
                listCellArround.add(this.getCell(0,1));
                listCellArround.add(this.getCell(1,1));
                listCellArround.add(this.getCell(1,0));
            } else if (boardCell.getPositionVertical() == 9) {
                listCellArround.add(this.getCell(0,8));
                listCellArround.add(this.getCell(1,8));
                listCellArround.add(this.getCell(1,9));
            } else {
                listCellArround.add(this.getCell(0,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(1,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(1,boardCell.getPositionVertical()+0));
                listCellArround.add(this.getCell(1,boardCell.getPositionVertical()+1));
                listCellArround.add(this.getCell(0,boardCell.getPositionVertical()+1));
            }
        } else if (boardCell.getPositionHorizontal() == 9) {
            if (boardCell.getPositionVertical() == 0) {
                listCellArround.add(this.getCell(8,0));
                listCellArround.add(this.getCell(8,1));
                listCellArround.add(this.getCell(9,1));
            } else if (boardCell.getPositionVertical() == 9) {
                listCellArround.add(this.getCell(9,8));
                listCellArround.add(this.getCell(8,8));
                listCellArround.add(this.getCell(8,9));
            } else {
                listCellArround.add(this.getCell(9,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(8,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(8,boardCell.getPositionVertical()+0));
                listCellArround.add(this.getCell(8,boardCell.getPositionVertical()+1));
                listCellArround.add(this.getCell(9,boardCell.getPositionVertical()+1));
            }
        }else {
            if (boardCell.getPositionVertical() == 0) {
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,0));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+0,1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,0));
            } else if (boardCell.getPositionVertical() == 9) {
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,9));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,8));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+0,8));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,8));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,9));
            } else {
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-0,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,boardCell.getPositionVertical()-1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,boardCell.getPositionVertical()+0));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()+1,boardCell.getPositionVertical()+1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-0,boardCell.getPositionVertical()+1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,boardCell.getPositionVertical()+1));
                listCellArround.add(this.getCell(boardCell.getPositionHorizontal()-1,boardCell.getPositionVertical()+0));
            }
        }
        for (BoardCell tBoardCell:listCellArround) {
            if (tBoardCell.getShip()!=null) {
                resultCheck = true;
                return resultCheck;
            }
        }
        return resultCheck;
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
    public Board getShipsBoardPlayer(Board.typeBoard type) {
        return type == Board.typeBoard.OPONENT ? ShipsBoardOponent  : shipsBoardPlayer;
    }
    public Player(String name) {
        this.name = name;
        Board ownBoar = new Board(this);
        setShipsBoardPlayer(ownBoar);
        Board oponentBoard = new Board(this);
        setShipsBoardOponent(oponentBoard);
        this.leftShip = new ArrayList<>();
    }

    public void setShipsBoardPlayer(Board shipsBoardPlayer) {
        this.shipsBoardPlayer = shipsBoardPlayer;
    }

    public void showOwnBoard() {

    }
    public void setShipsBoardOponent(Board shipsBoardOponent) {
        ShipsBoardOponent = shipsBoardOponent;
    }

    public void makeBoardPlayer() {
        this.shipsBoardPlayer  = new Board(this);
        this.ShipsBoardOponent = new Board(this);
    }
    public String requestCoordinates() {
        System.out.println("Enter the coordinates");
        String income = Main.incomeScaner.nextLine();
        return income;
    }
    public String requestCoordinates(Ship ship) {
        String resultMetod = "";
        String income = Main.incomeScaner.nextLine();
        String[] coordinatesArray = income.split(" ");
        if (coordinatesArray.length != 2) {
            System.out.println("Error! Wrong ship location! Try again:");
            return requestCoordinates(ship);
        };
        String firsCoorfinat   = coordinatesArray[0];
        String secondCoordinat = coordinatesArray[1];
        //check that is a line
        //1 line same liters
        //2 line same numbers
        int numberPartFirst = Integer.parseInt(firsCoorfinat.replaceAll("([a-zA-Z])", ""));
        String leterPartFirst  = firsCoorfinat.replaceAll("([0-9])", "");;
        int numberPartSecond = Integer.parseInt(secondCoordinat.replaceAll("([a-zA-Z])", ""));
        String leterPartSecond  = secondCoordinat.replaceAll("([0-9])", "");;

        if (numberPartFirst != numberPartSecond && !leterPartSecond.equals(leterPartFirst)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return requestCoordinates(ship);
        }else  if (leterPartFirst.length() == 1 && leterPartSecond.length() == 1 && leterPartSecond.equals(leterPartFirst)){
            if ((Board.allowedLetters.indexOf(leterPartFirst) % 10) == -1) {
                System.out.println("Error! Wrong ship location! Try again:");
                return requestCoordinates(ship);
            }else if (numberPartFirst > 10||numberPartSecond > 10) {
                System.out.println("Error! Wrong ship location! Try again:");
                return requestCoordinates(ship);
            }else if (numberPartFirst < 1||numberPartSecond < 1) {
                System.out.println("Error! Wrong ship location! Try again:");
                return requestCoordinates(ship);
            }else if ((Math.abs(numberPartFirst - numberPartSecond) + 1) != ship.getLength()) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n",ship.getName());
                return requestCoordinates(ship);
            }
            resultMetod = leterPartFirst + numberPartFirst + " " + leterPartFirst + numberPartSecond;
            return resultMetod;
             // leter line
        } else if (leterPartFirst.length() > 1 || leterPartSecond.length() > 1) {
            System.out.println("Error! Wrong ship location! Try again:");
            return requestCoordinates(ship);
        } else if (numberPartFirst <= 10 && numberPartSecond >= 1 && numberPartFirst == numberPartSecond) {
//            System.out.println("First - " + leterPartFirst + numberPartFirst);
//            System.out.println("Second - " + leterPartFirst + numberPartSecond);
            //line number
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return requestCoordinates(ship);
        }

//        for (String eachNumber:coordinatesArray) {
//            if (Integer.parseInt(numberPart) <= Main.sizeBoard && Integer.parseInt(numberPart) >0 ) {
//                //all good with number
//                ; }else {
//                ;
//            }
//
//        }
        return leterPartFirst + numberPartFirst + " " + leterPartSecond + numberPartSecond;
    }
    public String shoting(Player oponent) {
        String resultShoting = "";
        System.out.println("Take a shot!");

        return resultShoting;
    }
    public boolean isAllShipsSunk() {
        boolean result = false;
        return result;
    }
    public void printboard(Board.typeBoard typeBoard) {
        char showLeter = '@';
        Board referalBoard = this.getShipsBoardPlayer(typeBoard);
        for (int i = -1; i < Main.sizeBoard  ; i++,showLeter++) {
            for (int j = -1; j < Main.sizeBoard; j++) {
                if (i ==-1 && j == -1) {
                    System.out.print("  ");
                } else if ((i ==-1 || j == -1)) {
                    if (i==-1){
                        System.out.print("" + (j + 1));
                    }else {
                        System.out.print("" + showLeter);
                    }
                    System.out.print((j==(Main.sizeBoard-1))?"":" ");
                    //System.out.print("" + (i==-1?(j + 1):(char)showLeter ) + ((i==(Main.sizeBoard-1)||j==(Main.sizeBoard-1))?"":" "));
                } else {
                    System.out.print(referalBoard.getCell(i,j).getCellDisplay() + ((j==(Main.sizeBoard-1))?"":" "));
                }
            }
            System.out.println("");
        }
    }
    public void fillBoard() {
        for (Ship eachShip :
                Game.listShip) {
            this.printboard(Board.typeBoard.OWN);
            System.out.printf("Enter the coordinates of %s (%d cells):\n",eachShip.getName(),eachShip.getLength());
            while (this.getShipsBoardPlayer().placeShip(eachShip, requestCoordinates(eachShip)) == false) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            };
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
