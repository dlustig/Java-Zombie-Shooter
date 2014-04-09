import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Highs extends JFrame{
    private static final int WIDTH = 250;
    private static final int HEIGHT = 300;

    private JLabel s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    private JLabel message;
    private JButton svButton;
    private static String scoreText="";
    private static String scoreShow="";
    private static String [] x = Import.Import();


    public void Gui(){
    	// this opens a gui window to display the high scores and allows the player
    	// to save their score if they make the high score list
        svButton = new JButton("SAVE SCORES");
        svButton.setEnabled(false);
		int finalScore = GameWorld.getScore();

        int l = x.length;
        int [] scorearray = new int[l];


        String s = "";

        for(int i = 0; i < l; i++){
            scorearray[i] = Integer.parseInt(x[i]);
        }

        if(finalScore > scorearray[l-1]){
            scorearray[l-1] = finalScore;
            for(int y =0; y < l; y++){
                s = Integer.toString(scorearray[y]);
                scoreText=scoreText + s + "\n";
            }
            svButton.setEnabled(true);
            scoreShow =  "You made the high score list!\n";
        }
        else{
            scoreShow = "You didn't make the high scores.\n";
        }

        setTitle("HIGH SCORES");
        setSize(WIDTH, HEIGHT);
        message = new JLabel(scoreShow);
        message.setHorizontalAlignment(message.CENTER);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(12,1));

        s1 = new JLabel(x[0]);
        s1.setHorizontalAlignment(s1.CENTER);
        s2 = new JLabel(x[1]);
        s2.setHorizontalAlignment(s2.CENTER);
        s3 = new JLabel(x[2]);
        s3.setHorizontalAlignment(s3.CENTER);
        s4 = new JLabel(x[3]);
        s4.setHorizontalAlignment(s4.CENTER);
        s5 = new JLabel(x[4]);
        s5.setHorizontalAlignment(s5.CENTER);
        s6 = new JLabel(x[5]);
        s6.setHorizontalAlignment(s6.CENTER);
        s7 = new JLabel(x[6]);
        s7.setHorizontalAlignment(s7.CENTER);
        s8 = new JLabel(x[7]);
        s8.setHorizontalAlignment(s8.CENTER);
        s9 = new JLabel(x[8]);
        s9.setHorizontalAlignment(s9.CENTER);
        s10 = new JLabel(x[9]);
        s10.setHorizontalAlignment(s10.CENTER);

        pane.add(message);
        pane.add(s1);
        pane.add(s2);
        pane.add(s3);
        pane.add(s4);
        pane.add(s5);
        pane.add(s6);
        pane.add(s7);
        pane.add(s8);
        pane.add(s9);
        pane.add(s10);
        pane.add(svButton);

        svButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent sv){
				Highs.exportText();

            }
        });
		setVisible(true);
    }

    public static void exportText(){
        System.out.println(scoreText);
        Export Exporting = new Export(scoreText);
    }
    public static void main(String[] args){
        Highs frame = new Highs();
        frame.Gui();

    }


}
