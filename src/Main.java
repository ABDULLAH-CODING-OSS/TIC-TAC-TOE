import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarFile;

import static java.awt.Color.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static JFrame frame;
    static  JButton [] numberOfButtons=new JButton[9];
    static JTextField textField;
    static JPanel panel;
    static boolean player1Turn=true;
    static String[] board = new String[9];
    static boolean gameOver=false;



    public static void main(String[] args) {
        frame=new JFrame("TIC-TAC-TOE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(650,300,400,500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        ImageIcon image = new ImageIcon("D:\\ALL DATA OF CODING\\JAVA\\TIC-TAC-TOE-JAVA\\src\\OIP.jpeg");
        frame.setIconImage(image.getImage());


        Font myFont = new Font("my font ",Font.ITALIC,30);
        textField=new JTextField();
        textField.setBounds(43,375,300,50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);


        panel=new JPanel();
        panel.setBounds(43,28,300,325);
        panel.setLayout(new GridLayout(3,3,5,5));
        panel.setBackground(Color.BLACK);



        for (int i =0; i<9;i++) {
           final int index =i;
            numberOfButtons[i] = new JButton(" ");
            numberOfButtons[i].setFont(myFont);
            numberOfButtons[i].setFocusable(false);
            numberOfButtons[i].setBackground(Color.BLUE);
            numberOfButtons[i].setForeground(GREEN);
            numberOfButtons[i].setBorder(BorderFactory.createLineBorder(BLACK, 2));
            panel.add(numberOfButtons[i]);

       numberOfButtons[i].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (!gameOver && board[index]==null){

                   board[index]=player1Turn?"X":"O";
                   numberOfButtons[index].setText(board[index]);
                   numberOfButtons[index].setEnabled(false);
                   player1Turn=!player1Turn;
                   textField.setText(player1Turn?"PLAYER 1 TURN (X)":"PLAYER 2 TURN (0)");
                   checkForWinner();




               }
           }
       });

        }

           JButton resetButton = new JButton("Reset");
        resetButton.setBounds(150,435,100,30);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        frame.add(resetButton);

        frame.add(textField);
        frame.add(panel);
        panel.setVisible(true);
        frame.setFocusable(true);
        frame.setVisible(true);

    }
    public  static void checkForWinner(){
        String[][] winningCombination = {
                {board[0], board[1], board[2]},
                {board[3], board[4], board[5]},
                {board[6], board[7], board[8]},
                {board[0], board[3], board[6]},
                {board[1], board[4], board[7]},
                {board[2], board[5], board[8]},
                {board[0], board[4], board[8]},
                {board[2], board[4], board[6]},
        };
             for (String[]combo:winningCombination){
                 if (combo[0]!=null && combo[0].equals(combo[1]) && combo[1].equals(combo[2])){
                    gameOver=true;
                    textField.setText(combo[0]+"WINS!");
                    return;

                 }
             }
             boolean draw = true;
             for (String s : board){
                 if (s==null){
                     draw = false;
                     break;
                 }
             }
             if (draw){
                 gameOver=true;
                 textField.setText("It's a DRAW");
             }

        }


        public static void resetGame(){
        for (int i=0;i<9;i++){
            board[i]=null;
            numberOfButtons[i].setText(" ");
            numberOfButtons[i].setEnabled(true);
        }
        player1Turn=true;
        gameOver=false;
        textField.setText("PLAYER 1's TURN (X)");
        }
    }









