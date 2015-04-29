import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WarGUI extends JFrame
{
   // *** CONSTANTS *** //
   private static final int WIDTH = 800;
   private static final int HEIGHT = 800;
   
   // ** TRIVIAL ** //
   private String gameText = "Game in progress... Cards have been dealt evenly...";
   
   // **** IMPORTANT **** //
   private War game; //the game
   
   // *** PANELS *** //
   private JPanel topPanel,gamePanel,bottomPanel;
   
   // *** BUTTONS *** //
   private JButton drawButton;
   
   // *** LABELS **** // 
   private JLabel mainText; // instructions & messages at top
   private JLabel playerCard, computerCard, backCardOne, backCardTwo;
   
   private boolean war = false; // whether a war is happening or not
   
   /**
   Constructor
   */
   public WarGUI()
   {
      
      // *** SET LAYOUT *** //
      setLayout(new GridLayout(3,1));
      
      // *** INSTANTIATE GAME *** //
      game = new War();
      
      // *** INSTANTIATE PANELS *** // 
      topPanel = new JPanel();
      topPanel.setBackground(Color.green);
      
      gamePanel = new JPanel();
      gamePanel.setBackground(Color.blue);
      
      bottomPanel = new JPanel();
      bottomPanel.setBackground(Color.red);
      
      // *** INSTANTIATE LABELS *** //
      mainText = new JLabel(gameText);
      mainText.setFont(new Font("ARIAL", Font.BOLD,24));
      
      // *** INSTANTIATE BUTTON *** //
      drawButton = new JButton("Draw");
      drawButton.addActionListener(new ButtonListener());
      
      // *** INSTANTIATE CARD LABELS ** //
      backCardOne = new JLabel(new ImageIcon("gfx/back.jpg"));
      playerCard = new JLabel(new ImageIcon("gfx/back.jpg"));
      computerCard = new JLabel(new ImageIcon("gfx/back.jpg"));
      backCardTwo = new JLabel(new ImageIcon("gfx/back.jpg"));
      
      // ADD TO PANE
      topPanel.add(mainText);
      gamePanel.add(backCardOne);
      gamePanel.add(playerCard);
      gamePanel.add(computerCard);
      bottomPanel.add(drawButton);
      gamePanel.add(backCardTwo);
      
      // ADD PANELS TO WINDOW
      add(topPanel);
      add(gamePanel);
      add(bottomPanel);
      
      // CONFIG
      setTitle("War Card Game");
      setSize(WIDTH,HEIGHT);
      setVisible(true);
      backCardOne.setVisible(false);
      backCardTwo.setVisible(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   /**
   ButtonListener
   Checks if button was pressed
   */
   private class ButtonListener implements ActionListener
   {
      /**
      actionPerformed
      @param e the event
      */
      public void actionPerformed(ActionEvent e)
      {
         // if war is true, show the upside-down cards
         if(war == true)
         {
            backCardOne.setVisible(true);
            backCardTwo.setVisible(true);
         }
         // else dont
         else if(war == false)
         {
            backCardOne.setVisible(false);
            backCardTwo.setVisible(false);
         }
         
         // draw the window and cards
         drawButton.setText("Draw");
         mainText.setText("Drawing...");
         game.draw(war);
         String playerPath = "gfx/" + game.getCardImage(0) + ".jpg";
         String computerPath = "gfx/" + game.getCardImage(1) + ".jpg";
         
         // compare cards
         int compare = game.compare();
         
         // do something based off this
         if(compare == 0)
         {
            mainText.setText("Player wins!");
            war = false;
         }
         else if(compare == 1)
         {
            mainText.setText("Computer wins!");
            war = false;
         }
         else if(compare == 3)
         {
            mainText.setText("War!");
            drawButton.setText("War");      
            playerCard.setIcon(new ImageIcon(playerPath));
            computerCard.setIcon(new ImageIcon(computerPath));
            war = true;
            backCardOne.setVisible(true);
            backCardTwo.setVisible(true);
         }
         else if(compare == 4)
         {
            if(game.getHandAmount(0) == 0)
            {
               mainText.setText("Computer wins the game of War!");
               drawButton.setVisible(false);
            }
            else if(game.getHandAmount(1) == 0)
            {
               mainText.setText("Player wins the game of War!");
               drawButton.setVisible(false);
            }
         }
         
         playerCard.setIcon(new ImageIcon(playerPath));
         computerCard.setIcon(new ImageIcon(computerPath));
         
         System.out.println(game.getHandSizes());
      }
   }
   
   /**
   setText
   @param yourText the text you'd like to set
   */
   public void setText(String yourText)
   {
      gameText = yourText;
   }
}
