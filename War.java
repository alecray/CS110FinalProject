import java.util.*;

// runs the war game itself
public class War
{
   // Arraylist to hold the cards on the table
   private ArrayList<Card> tableCards = new ArrayList<Card>();
   
   // initialize deck
   private Deck myDeck = new Deck();
   
   /**
   Constructor
   */
   public War()
   {
      // shuffle the deck
      myDeck.shuffle();
      // deal the deck
      myDeck.deal();
      
   }
   
   /**
   draw
   Draws cards from the deck, clears table cards if there isn't a war
   @param isWar is there a war happening
   */
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
   
   /**
   getCardImage
   @param playerOrComputer player or computer card image?
   @return the name of the card file
   */
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
   
   /**
   getHandAmount
   the number of cards in hand
   @param playerOrComputer player or computer hand?
   @return the number of cards in hand
   */
   public int getHandAmount(int playerOrComputer)
   {
         return myDeck.getHandSize(playerOrComputer);
   }
   
   /**
   compare
   @return whether the player wins or the computer wins the round
   */
   public int compare()
   {
      // result of two cards being drawn, initialized to un-used number
      int result = 5;
      try
      {
         // compare the cards
         result = myDeck.check(tableCards.get(0),tableCards.get(1));
         
         // do something based off result
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
      }
      // if either of the hands are empty, the game ends
      catch(ArrayIndexOutOfBoundsException e)
      {
         result = 4;
      }
      return result;
      
   } 
   
   /**
   getHandSizes
   get the size of both hands as a string. this was for debugging.
   @return the sizes of both hands as a string
   */
   public String getHandSizes()
   {
      String totalNum = Integer.toString(myDeck.getHandSize(0) + myDeck.getHandSize(1));
      String playerHandSize = Integer.toString(myDeck.getHandSize(0));
      String computerHandSize = Integer.toString(myDeck.getHandSize(1));
      
      String full = "Player: " + playerHandSize + ", Computer: " + computerHandSize + ", Total: " + totalNum;
      
      return full;
   }
}