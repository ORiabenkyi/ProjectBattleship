
public class Main {
    public static final int sizeBoard = 10;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var game = new Game();
        game.start();
    }

class Game {
    board board;
    Player p1;
    Player p2;
        Game() {
            p1 = new ConsolePlayer();
//            p2 = new MailPlayer();
            shipsBoard = new board(p1);
            shotsBoard = new board(p1);
        }
        public void start() {
            for (Ship ship :
                    ships) {
                board.placeShip(ship, player.askShip())
            }
            finished = false;
            while(!finished) {
                var valid = false;
                player.printboard();
                while(!valid) {
                    var shot = player.getShot();
                    valid = true;
                }
                player.applyShot(shot);
                shotResult = opponent.Shot(shot);
                print shotResult

                player = player == p1 ? p2 : p1;
                opponent = player == p1 ? p2 : p1;
                finished = true;
            }
        }
}
class boardCell {
    protected String name;
    protected char cellDisplay;
    protected String color;
    protected ship Ship;
    protected int positionHorizontal, positionVertical;

    public ship getShip() {
        return Ship;
    }

    public boardCell(String name) {
        this.name = name;
    }

    public boardCell(String name, char cellDisplay) {
        this.name = name;
        this.cellDisplay = cellDisplay;
    }

    public boardCell(String name, char cellDisplay, String color, ship ship) {
        this.name = name;
        this.cellDisplay = cellDisplay;
        this.color = color;
        Ship = ship;
    }
}

class board {
    protected player player;
    protected boardCell[][] board;

    protected initializeBoard() {
        board = new boardCell[sizeBoard][sizeBoard];
        for (int i = 0; i <sizeBoard ; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                //board[i][j] = new boardCell();

            }
            ;
        }
    }
    public boolean isEmpty(int x, int y){}
    public placeShip(Ship ship, Position start, Position end) {
        for (:
             ) {
            boardCell[0].ship = ship
        }
    }
}

class player {

}

class ship {

}
}