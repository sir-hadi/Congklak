import java.util.Arrays; 
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdullah Hadi
 */
public class Congklak {

    static int[] board;
    static int[] emptyBoard;
    static int n;
    static int s;
    static int score1;
    static int score2;

    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input number of small holes : ");
        n = scan.nextInt();
        System.out.print("Input number of seed in holes : ");
        s = scan.nextInt();
        
        board = new int[(n * 2)];
        emptyBoard = new int[(n * 2)];
        
        //fills board with s number on every element/index array
        fillBoard(board, s);
        fillBoard(emptyBoard, 0);
        
        //Loop while arrays value is not 0 on every element/index array
        while (!Arrays.equals(board, emptyBoard)) {
            play(1);
            System.out.println("====================");
            viewBoard();
            System.out.println("====================");
            play(2);
            System.out.println("====================");
            viewBoard();
            System.out.println("====================");
        }

    }

    private static void fillBoard(int[] board, int s) {
        for (int i = 0; i < board.length; i++) {
            board[i] = s;
        }
    }

    private static void viewBoard() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(i + " - " + board[i]);
        }
    }

    private static void play(int player) {
        boolean playing = true;
        int pos = choosePos(player); // choose first pos
        int hand = board[pos]; // fill hand from pos
        board[pos] = 0; // empty the board pos
        while (playing) { // will keep going until playing 0
            if (pos < board.length - 1) {
                pos++; // pos next            
            } else {
                pos = 0;
            }
            hand--; // hand -1
            board[pos]++; // board +1
            if (player == 1 && pos == (n - 1) && hand > 0) { // check when a. player 1, b. exactly on n-1, c. hand not
                // empty
                score1++; // add score
                hand--; // hand -1
                if (hand <= 0) { // if hand became empty, take another position
                    pos = choosePos(player);
                    hand = board[pos];
                    board[pos] = 0;
                }
            } else if (player == 2 && pos == (2 * n - 1) && hand > 0) { // check when a. player 2, b. exactly on 2n-1,
                // c. hand not empty
                score2++; // add score
                hand--; // hand -1
                if (hand <= 0) { // if hand became empty, take another position
                    pos = choosePos(player);
                    hand = board[pos];
                    board[pos] = 0;
                } else {
                    pos = -1; // edge of board (making sure no overload)
                }
            }
            if (hand == 0) { // when hand became empty
                if (board[pos] > 1) { // if the current board position bigger than 1 (previously not 0) take them all
                    hand = board[pos];
                    board[pos] = 0;
                } else { // else its over
                    playing = false;
                }
            }
        }
    }

    public static int choosePos(int player) {
        int start, end;
        if (player == 1) {
            start = 0;
            end = n; // biar gak out of bound arraynya
        } else {
            start = n;
            end = (2 * n); // biar gak out of bound arraynya
        }
        int max = 0;
        for (int i = start; i < end; i++) {
            if (board[i] > board[max]) {
                max = i;
            }
        }
        return max;
    }

}
