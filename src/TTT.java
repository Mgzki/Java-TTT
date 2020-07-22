public class TTT{
    public static void main(String[] args){
        Board TTT = new Board();
        int[] move = {0,0};
        TTT.set_Move(move, 'X');
        System.out.println(TTT.get_Move(move));
    }
}
