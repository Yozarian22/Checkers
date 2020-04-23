import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Board board;
    Player red;
    Player black;
    int turn;
    Scanner scanner;

    public Game(){
        scanner = new Scanner(new InputStreamReader(System.in));
        board = new Board();
        red = new Player();
        black = new Player();
        turn = 0;
    }

    public void runGame() {
        while (!isWinningState()) {
            System.out.println(board);
            List<int[]> moves = promptMove(turn);
            if (checkValidMoves(moves)){
                board.update(moves);
                turn += 1;
                turn = turn%2;
            }
            else { System.out.println("bad"); }
    }
    }

    public List<int[]> promptMove(int turn){
        if (turn == 0){
            System.out.println("red move");
        } else {
            System.out.println("Black Move");
        }
        String input = scanner.nextLine();
        return Game.parseMove(input);
    }
    private boolean isWinningState(){
        return false;
   }

    public static List<int[]> parseMove(String input){
        //"x0,y0 x1,y1"
        String[] inputList = input.split(" ");
        //["x0,y0","x1,y1"]
        List<int[]> parsed = new ArrayList<>();
        for (String stringCoord: inputList){
            int[] coord = new int[2];
            String[] split = stringCoord.split(",");
            coord[0] = Integer.parseInt(split[0]);
            coord[1] = Integer.parseInt(split[1]);
            parsed.add(coord);
        }
        return parsed;
    }

    public boolean checkValidMoves(List<int[]> moves) {
        // move is (x,y)
        // check if all moves are lenght of 2 and x,y locations are 0-7
        for (int[] move : moves) {
                if (move.length != 2) {
                    return false; }
                if (move[0] < 0 || move[0] > 7 || move[1] < 0 || move[1] > 7){
                    return false; }
        }
        for (int[] move : moves.subList(1,moves.size() - 1)) {
                if (!isEmpty(move)){
                    return false;
                }
        }
        int numMoves = moves.size() - 1;
        int[] start = moves.get(0);
        int[] secondPosition = moves.get(1);
        // selected square has piece of color of current player
        if (!isValidChecker(moves.get(0))) {
            return false;
        }
        // jump
        else if (isJump(start,secondPosition)){ 
            // check if all valid jumps
            //return checkAllJumps(moves);

        }
        // no jump
        else if (numMoves == 1){
               // return false;
        }
        return true;
    }

    public boolean isEmpty(int[] move) {
        return (board.board[move[0]][move[1]].piece == null);
    }
    public boolean isJump(int[] first, int[] second){
        return ((xDistance(first, second) == 2) && (yDistance(first, second) == 2));
        }
    public int xDistance(int[] start, int[] end) {
        return Math.abs(end[0] - start[0]);
        }
     public int yDistance(int[] start, int[] end) {
        return Math.abs(end[1] - start[1]);
        }
    
    public boolean isValidChecker(int[] coord) {
        int x = coord[0];
        int y = coord[1];
        Board.Square s = board.board[x][y];
        if (s.piece == null){
            return false;
        }
        return s.piece.color == turn;
    }

     



}
