import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ZombieAttack extends JFrame{

    boolean pause = false;
    // initializes gui components and starts the background music/sounds
    public ZombieAttack() {
        initComponents();
        SoundManager.startBackgroundMusic();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        clearPanel = new javax.swing.JPanel();
        qButton = new javax.swing.JButton();
        scoreLabel = new javax.swing.JLabel();
        expShow = new javax.swing.JLabel();
        scoreShow = new javax.swing.JLabel();
        healthLabel = new javax.swing.JLabel();
        accLabel = new javax.swing.JLabel();
        lvlLabel = new javax.swing.JLabel();
        expLabel = new javax.swing.JLabel();
        lvlShow = new javax.swing.JLabel();
        healthShow = new javax.swing.JLabel();
        accShow = new javax.swing.JLabel();
        board = new javax.swing.JPanel();

        enemyClass ec = new enemyClass(board);
        jLabel1 = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 768));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

	// sets up a clear jpanel so that the animations will show on top of the background image
        clearPanel.setOpaque(false);
        board.setOpaque(false);
        clearPanel.setPreferredSize(new java.awt.Dimension(202, 597));


        qButton.setText("QUIT");
        qButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent qt){
                System.exit(0);
            }

        });




        scoreLabel.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("SCORE");

        expShow.setFont(new java.awt.Font("Lucida Grande", 2, 30)); // NOI18N
        expShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        expShow.setText("");

        scoreShow.setFont(new java.awt.Font("Lucida Grande", 2, 30)); // NOI18N
        scoreShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreShow.setText("0");

        healthLabel.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        healthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        healthLabel.setText("HEALTH");

        accLabel.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        accLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accLabel.setText("ACC");

        lvlLabel.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        lvlLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lvlLabel.setText("LEVEL");

        expLabel.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        expLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        expLabel.setText("");

        lvlShow.setFont(new java.awt.Font("Lucida Grande", 2, 30)); // NOI18N
        lvlShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lvlShow.setText("1");

        healthShow.setFont(new java.awt.Font("Lucida Grande", 2, 30)); // NOI18N
        healthShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        healthShow.setText("10");

        accShow.setFont(new java.awt.Font("Lucida Grande", 2, 30)); // NOI18N
        accShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accShow.setText("0");
	
	// sets layout with the button and labels

        javax.swing.GroupLayout boardLayout = new javax.swing.GroupLayout(board);
        board.setLayout(boardLayout);
        boardLayout.setHorizontalGroup(
                                       boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGap(0, 606, Short.MAX_VALUE)
                                       );
        boardLayout.setVerticalGroup(
                                     boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                     .addGap(0, 0, Short.MAX_VALUE)
                                     );
        javax.swing.GroupLayout clearPanelLayout = new javax.swing.GroupLayout(clearPanel);
        clearPanel.setLayout(clearPanelLayout);
        clearPanelLayout.setHorizontalGroup(
                                            clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(clearPanelLayout.createSequentialGroup()
                                                      .addContainerGap()
                                                      .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                                      .addGroup(clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(scoreLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                                                .addComponent(expLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(scoreShow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(expShow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lvlLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lvlShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(healthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(healthShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(accLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(accShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(qButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                      .addGap(14, 14, 14))
                                            );
        clearPanelLayout.setVerticalGroup(
                                          clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(clearPanelLayout.createSequentialGroup()
                                                    .addGroup(clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                              .addGroup(clearPanelLayout.createSequentialGroup()
                                                                        .addContainerGap(35, Short.MAX_VALUE)
                                                                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(scoreShow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(expLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(expShow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(lvlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(lvlShow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(healthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(healthShow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(accLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(accShow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(126, 126, 126)
                                                                        .addComponent(qButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(55, 55, 55))
                                                              .addGroup(clearPanelLayout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addContainerGap())
                                          );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        getContentPane().add(clearPanel, gridBagConstraints);

	// sets the jlabel to have the field1 image 
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newpackage/field1.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        getContentPane().add(jLabel1, gridBagConstraints);

        pack();


    }

	// functions to set the jlabels with the correct values from the program 
	// as it is running
	public static void setExp(int exp) {
		expShow.setText(""+exp);
	}

	public static void setScore(int score) {
		scoreShow.setText(""+score);
	}

	public static void setLvl(int lvl) {
		lvlShow.setText(""+lvl);
	}

	public static void setHealth(int health) {
		healthShow.setText(""+health);
	}

	public static void setAcc(double val) {
		accShow.setText(""+(int)(val * 100)/100.0);
	}

	public void updateLabels() {
		setAcc(GameWorld.getAcc());
	}

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	// makes a new object to call the main gui
                ZombieAttack ZA = new ZombieAttack();
                ZA.setVisible(true);
            }
        });
    }

    private javax.swing.JLabel accLabel;
    private static javax.swing.JLabel accShow;
    private javax.swing.JPanel board;
    private javax.swing.JPanel clearPanel;
    private javax.swing.JLabel expLabel;
    private static javax.swing.JLabel expShow;
    private javax.swing.JLabel healthLabel;
    private static javax.swing.JLabel healthShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lvlLabel;
    private static javax.swing.JLabel lvlShow;
    private javax.swing.JButton qButton;
    private javax.swing.JLabel scoreLabel;
    private static javax.swing.JLabel scoreShow;

}
