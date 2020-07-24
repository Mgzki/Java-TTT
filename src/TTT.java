import java.util.Scanner;

public class TTT{
    //Changes the turn, if True it's the human players turn, otherwise AI turn
    public static boolean change_Turn(boolean userTurn){
        userTurn = !userTurn;
        return userTurn;
    }
    //Randomly selects who goes first
    public static boolean goes_First(){
        boolean userTurn = (System.currentTimeMillis() % 2) == 0;
        return userTurn;
    }
    //Checks the entire row, column, and both diagonals of the last move for a winning state
    public static boolean check_Win(Board board, Character player){
        for (int i = 0; i < 3; i++){
            if (board.check_Row(i, player))
                return true;
            else if (board.check_Col(i, player))
                return true;
            else if (board.check_Diag(0, player))
                return true;
            else if (board.check_Diag(1, player))
                return true;
        } return false;
    }
    public static int[] convert_Move(int move){
        switch(move){
            case 1: return (new int[] {0,0});
            case 2: return (new int[] {0,1});
            case 3: return (new int[] {0,2});
            case 4: return (new int[] {1,0});
            case 5: return (new int[] {1,1});
            case 6: return (new int[] {1,2});
            case 7: return (new int[] {2,0});
            case 8: return (new int[] {2,1});
            case 9: return (new int[] {2,2});
        } return new int[] {0,0};
    }

    public static Board get_Move(Board board, boolean userTurn){
        int[] move = new int[2];
        if (userTurn){
            System.out.println("Enter your move (1-9)");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            move = convert_Move(i);
            if (board.set_Move(move, 'X')){
                ;
            } else {get_Move(board, userTurn);}
        } else{ //AI move
            System.out.println("Opponent is deciding");
            board = bot_Move(board);
        }
        return board;
    }
    public static Board bot_Move(Board board){
        double bestScore = Double.NEGATIVE_INFINITY;
        int [] move = new int[2];
        int [] bestMove = new int[2];
        for (int i = 0; i < 9; i++){
            move = convert_Move(i+1);
            if (board.set_Move(move,'O')){
                double score = miniMax(board, 0, false);
                board.reset_Move(move, i);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        board.set_Move(bestMove,'O');
        return board;
    }
    public static double miniMax(Board board, int depth, boolean userTurn){
        int[] move = new int[2];
        if (check_Win(board, 'O')) return 1; //O wins
        if (check_Win(board, 'X')) return -1;//X wins
        if (board.full_Board()) return 0;//Tie
        //Maximizing turn (AI)
        if (userTurn){
            double bestScore = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 9; i++){
                move = convert_Move(i+1);
                if (board.set_Move(move, 'O')){
                    double score = miniMax(board, depth+1, false);
                    board.reset_Move(move, i);
                    if (score > bestScore)
                        bestScore = score;
                }
            }
            return bestScore;
        } else {
            double bestScore = Double.POSITIVE_INFINITY;
            for (int i = 0; i < 9; i++){
                move = convert_Move(i+1);
                if (board.set_Move(move, 'X')){
                    double score = miniMax(board, depth+1, true);
                    board.reset_Move(move, i);
                    if (score < bestScore)
                        bestScore = score;
                }
            }
            return bestScore;
        }
    }
    public static void main(String[] args){
        Board TTT = new Board();
        TTT.display();
        boolean userTurn = goes_First();
        while (!check_Win(TTT,'O') && !check_Win(TTT, 'X') && !TTT.full_Board()) {
            TTT = get_Move(TTT, userTurn);
            userTurn = change_Turn(userTurn);
            TTT.display();
        }
        TTT.display();
        if (check_Win(TTT, 'O')) System.out.println("Computer wins!");
        if (check_Win(TTT, 'X')) System.out.println("You win!");
        if (TTT.full_Board()) System.out.println("Tie game");
    }
}
