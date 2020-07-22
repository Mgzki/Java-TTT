public class Board {
    private char[][] board; // 2D array of chars

    //Constructs the 3 x 3 grid with enumerated empty cells for the playing board
    public Board() {
        board = new char[3][3];
        int counter = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = (char)(counter + 48); //converts (int)counter to associated digit as char
                counter++;
            }
        }
    }

    //Constructs an N by N grid with enumerated empty cells for the playing board
    public Board(int n) {
        board = new char[n][n];
        int counter = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = (char)(counter + 48);
                counter++;
            }
        }
    }

    //Sets the players move in the selected cells
    public boolean set_Move(int[] move, char player) {
        if (board[move[0]][move[1]] != 'X' || board[move[0]][move[1]] != 'O') {
            board[move[0]][move[1]] = player;
            return true;
        } else {
            System.out.println("Invalid move");
            return false;
        }
    }
    public char get_Move(int [] move){
        return board[move[0]][move[1]];
    }
}

