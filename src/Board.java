import java.util.List;

public class Board {
    public static int boardWidth = 8;
    public static int boardLength = 8;
    Square[][] board;

    public Board() {

        board = new Square[boardLength][boardWidth];
        for (int j = 0; j < boardLength; j++) {
            for (int i = 0; i < boardWidth; i++) {
                board[i][j] = new Square();

                if (i == 0 || i == 2) {
                    if (j % 2 == 0) {
                        board[i][j].piece = new Piece(Piece.RED);
                    }
                } else if (i == 1) {
                    if (j%2 == 1) {
                        board[i][j].piece = new Piece(Piece.RED);
                    }
                } else if (i == 5 || i == 7) {
                    if (j%2 == 1) {
                        board[i][j].piece = new Piece(Piece.BLACK);
                    }
                } else if (i == 6) {
                    if (j%2 == 0) {
                        board[i][j].piece = new Piece(Piece.BLACK);
                    }
                }
            }
        }
    }

    public void update(List<int[]> moves) {
        boolean valid = checkValidMoves(moves);
            int[] st = moves.get(0);
            Square start = board[st[0]][st[1]];

            int[] dest = moves.get(moves.size() - 1);
            Square destination = board[dest[0]][dest[1]];

            destination.piece = start.piece;
            start.piece = null;

            for (int i = 0; i < moves.size() - 1; i++) {
                st = moves.get(i);
                dest = moves.get(i + 1);

                if (Math.abs(st[0] - dest[0]) > 1) { //If this move is a jump
                    int jumpX = (st[0] + dest[0]) / 2;
                    int jumpY = (st[1] + dest[1]) / 2;

                    Square jumped = board[jumpX][jumpY];
                    jumped.piece = null;
                }
            }
    }

    public boolean checkValidMoves(List<int[]> moves) {
        // move is (x,y)
        /*
        if (!isColoredChecker(moves.get(0), game.turn)) {
            return false;
        }
        else if (xDistance(moves.get(0), moves.get(1)) > 1) {
            // check if all valid jumps
        }
        else {
            // not jump
            if (yDistance(moves.get(0), moves.get(1)) != 1) {
                return false;
            }
        }
         */
        return true;
    }

    public boolean isColoredChecker(int[] coord,int color) {
        int x = coord[0];
        int y = coord[1];
        Square s = board[x][y];
        if (s.piece == null){
            return false;
        }
        return s.piece.color == color;
    }

    public String toString() {
        String s = "  0  1  2  3  4  5  6  7 \n";
        for (int i = 0; i < boardLength; i++) {
                s += i;
            for (int j = 0; j < boardWidth; j++) {
                Piece p = board[i][j].piece;
                if (p == null) {
                    s += "[ ]";
                } else if (p.color == Piece.RED) {
                    s += "[R]";
                } else if (p.color == Piece.BLACK) {
                    s += "[B]";
                }
            }
            s += '\n';
        }
        return s;
    }

    public class Square {
        Piece piece;
    }
}
