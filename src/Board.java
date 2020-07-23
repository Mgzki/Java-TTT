public class Board {
    private final char[][] board; // 2D array of chars

    //Constructs the 3 x 3 grid with enumerated empty cells for the playing board
    public Board() {
        board = new char[3][3];
        int counter = 1; //Moves are from 1-9
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = (char)(counter + 48); //converts (int)counter to associated digit as char
                counter++;
            }
        }
    }
    //Sets the players move in the selected cells
    public boolean set_Move(int[] move, char player) {
        if (cell_Free(move)) {
            board[move[0]][move[1]] = player;
            return true;
        } else {
            //System.out.println("Invalid move");
            return false;
        }
    }
    public void reset_Move(int[]move, int reset){
        board[move[0]][move[1]] = (char)(reset + 48);
    }
    //Gets the current value from a position on the board
    public char get_Move(int [] move){
        return board[move[0]][move[1]];
    }
    //Checks if the cell is free
    public boolean cell_Free(int[] move){
        return (board[move[0]][move[1]] != 'X' && board[move[0]][move[1]] != 'O');
    }
    /*Checks if there are any available moves
     *Used for tie checking in Tic Tac Toe
     */
    public boolean full_Board(){
        int[] temp = new int[2];
        for (int i = 0; i < 3; i++){
            temp[0] = i;
            for (int j = 0; j < 3; j++){
                temp[1] = j;
                if(cell_Free(temp))
                    return false;
            }
        }return true;
    }
    //Displays the board state
    public void display(){
        // display the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col]);
                if (col < board.length - 1)
                    System.out.print("|");
            }
            if (row < board.length - 1)
                System.out.println("\n-----");
            else
                System.out.println();
        }
        System.out.println();
    }
    public boolean check_Row(int row, char player){
        for (int col = 0; col < 3; col++){
            if (board[row][col] != player)
                return false; //cell didn't match
        }
        return true; // must be 3 in a row
    }
    public boolean check_Col(int col, char player){
        for (int row = 0; row < 3; row++){
            if (board[row][col] != player)
                return false; //cell didn't match
        }
        return true; // must be 3 in a row
    }
    public boolean check_Diag(int diag, char player){
        if (diag == 0){//Top left to bottom right
            for (int diag1 = 0; diag1 < 3; diag1++){
                if (board[diag1][diag1] != player)
                    return false; //cell didn't match
            }
        } else {//Top right to bottom left
            for (int diag2 = 0; diag2 < 3; diag2++) {
                if (board[diag2][2 - diag2] != player)
                    return false; //cell didn't match
            }
        } return true; // must be 3 in a row
    }
}

