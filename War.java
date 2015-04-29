import java.util.*;

public class War
{
   private ArrayList<Card> tableCards = new ArrayList<Card>();
   
   // initialize deck
   private Deck myDeck = new Deck();
   
   public War()
   {
      // shuffle the deck
      myDeck.shuffle();
      // deal the deck
      myDeck.deal();
      
   }

   public void draw(boolean isWar)
   {
      if(isWar == false)
      {
         tableCards.clear();
      }
      
      // player draws card
      tableCards.add(0,myDeck.drawCard(0,0)); // player is card 0
      System.out.println("Player draws a " + tableCards.get(0));

      // computer draws card
      tableCards.add(1,myDeck.drawCard(1,0)); // computer is card 1
      System.out.println("Computer draws a " + tableCards.get(1));
   }
   
   public String getCardImage(int playerOrComputer)
   {
      String myString = "";
      
      if(playerOrComputer == 0)
      {
         Card tempCard = tableCards.get(0);
         myString = tempCard.toString();
         
      }
      else if(playerOrComputer == 1)
      {
         Card tempCard = tableCards.get(1);
         myString = tempCard.toString();
      }
      
      return myString;
   }
   
   public int getHandAmount(int playerOrComputer)
   {
         return myDeck.getHandSize(playerOrComputer);
   }
   /*
   public void war()
   {
      
   }
   */
   public int compare()
   {
      // result of two cards being drawn, initialized to un-used number
      int result = 5;
      try
      {
         result = myDeck.check(tableCards.get(0),tableCards.get(1));
         
         if(result == 0)
         {
            System.out.println("Player wins!");
            myDeck.shift(1,0);
            myDeck.shift(1,1);
            myDeck.addCards(tableCards,0);
         }
         else if(result == 1)
         {
            System.out.println("Computer wins!");
            myDeck.shift(1,1);
            myDeck.shift(1,0);
            myDeck.addCards(tableCards,1); 
         }
         else if(result == 3)
         {
            System.out.println("War!!!");
            myDeck.shift(1,1);
            myDeck.shift(1,0);
         }
            /*
            boolean war = true;
            while(war == true)
            {
               
               int whoWins = myDeck.war(tableCards.get(1),tableCards.get(0)); // player, computer
               if (whoWins == 0)
               {
               
                  System.out.println("Player wins war!");
                  myDeck.addCards(tableCards,0);
                  war = false;
               }
               else if (whoWins == 1)
               {
                  System.out.println("Computer wins war!");
                  myDeck.addCards(tableCards,1);
                  war = false;
               }
               else
               { 
                  System.out.println("War again!");
               }
            }
            
            
         }
         else
         {
            System.out.println("Error.");
         }*/
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
         result = 4;
      }
      return result;
      
   } 
   
   public String getHandSizes()
   {
      String totalNum = Integer.toString(myDeck.getHandSize(0) + myDeck.getHandSize(1));
      String playerHandSize = Integer.toString(myDeck.getHandSize(0));
      String computerHandSize = Integer.toString(myDeck.getHandSize(1));
      
      String full = "Player: " + playerHandSize + ", Computer: " + computerHandSize + ", Total: " + totalNum;
      
      return full;
   }
}