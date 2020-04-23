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
            board.update(moves);
            turn += 1;
            turn = turn%2;
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




}
