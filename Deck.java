import java.util.*;

/**
Class that controls the deck for the War game
*/
public class Deck
{
   // create arrays to hold cards
   private Card[] deckArray = new Card[52];
   private Card[] playerHand = new Card[52];
   private Card[] computerHand = new Card[52];
   
   /**
   Constructor
   */
   public Deck()
   {
      int currCard = 0; //to create the deck
      
      //create deck
      for(int i=0;i<=3;i++)
      {
         for(int j=2;j<15;j++)
         {
            deckArray[currCard] = new Card(i,j);
            currCard+=1;
         }
         
      }  
       
   }
   
   /**
   Shuffle
   shuffles the deck of cards
   */
   public void shuffle()
   {
      // shuffle the array
      Collections.shuffle(Arrays.asList(deckArray));
   }
   
   /**
   Deal
   splits the deck array between computer and player
   */
   public void deal()
   {
      // for each item in the deck array, split it between player and computer
      for(int i=0;i<26;i++)
      {
         playerHand[i] = deckArray[i];
         computerHand[i] = deckArray[i+26];
      }
   }
   
   /**
   Card
   picks a card from the hand of the player or computer
   @param playerOrComputer Pass in a value for whether you want to pick a player card or computer card
   @param index The position of the card in the array
   @return the card at specific index
   */
   public Card drawCard(int playerOrComputer, int index) // pass in 0 for player, 1 for computer
   {
      int ZERO = 0;
      
      //temporary card to assign new values to
      Card tempCard = new Card(0,0);
      
      // if player
      if (playerOrComputer == ZERO)
      {
         //try to get the card if it exists
         try
         {
            tempCard = new Card(playerHand[index].getSuit(),playerHand[index].getRank());
            return tempCard;
         }
         catch (NullPointerException e)
         {
            System.out.println("Player has no cards. Computer wins!");
         }
      }
      
      // if computer
      else
      {
         //try to get the card if it exists
         try 
         {
            tempCard = new Card(computerHand[index].getSuit(),computerHand[index].getRank());
            return tempCard;
         }
         catch (NullPointerException e)
         {
            System.out.println("Computer has no cards. Player wins!");
         }
      }
      
      return tempCard;
   }
   
   /**
   toString
   Converts the whole deck to a string
   @return the deck string
   */
   public String toString()
   {
      String deckString = "";
      
      for(int i=0;i<52;i++)
      {
         deckString = deckString + deckArray[i].toString() + ", ";
      }
      
      return deckString;
   }
   
   /**
   shift
   moves the cards in a hand so that old cards that have been placed are removed
   @param times the number of times to shift the hand
   @param playerOrComputer for player or computer?
   */
   public void shift(int times, int playerOrComputer) // 0 for player, 1 for computer 
   {
      // temporary hand to hold the new shifted hand
      Card[] tempCard = new Card[52];
      
      // if player
      // shift all cards and save in new tempdeck
      if(playerOrComputer == 0){
         for(int j=0;j<times;j++){
            for(int i=0;i<51;i++){
               tempCard[i] = playerHand[i+1];
            }
            
            for(int i=0;i<52;i++){
               playerHand[i] = tempCard[i];
            }
         }
      }
      // if computer
      // shift all cards and save in new tempdeck
      else if(playerOrComputer == 1){
         for(int j=0;j<times;j++){
            for(int i=0;i<51;i++){
               tempCard[i] = computerHand[i+1];
            }
            
            for(int i=0;i<52;i++){
               computerHand[i] = tempCard[i];
            }
         }
      }
   }
   
   /**
   getCardRankAtArrayIndex
   @param yourArrayIndex the index of your card
   @param playerOrComputer whether you want to check player or computer hand
   @return the index of the card
   */
   public int getCardRankAtArrayIndex(int yourArrayIndex, int playerOrComputer) // pass in 0 for player, 1 for computer
   {
      int ZERO = 0;
      
      // if player 
      if (playerOrComputer == ZERO)
      {
         try
         {
            return playerHand[yourArrayIndex].getRank();      
         }
         catch (NullPointerException e)
         {
            return ZERO; 
         }
      }
      
      // if computer
      else
      {
         try
         {
            return computerHand[yourArrayIndex].getRank();      
         }
         catch (NullPointerException e)
         {
            return ZERO; 
         }
      }
   }
   
   /**
   setCard
   sets a card at the specified index
   @param yourArrayIndex the index of your card
   @param yourCard the card object itself
   @param playerOrComputer player or computer card?
   */
   public void setCard(int yourArrayIndex, Card yourCard, int playerOrComputer) // pass in 0 for player, 1 for computer
   {
      int ZERO = 0;
      
      if (playerOrComputer == ZERO)
      {
         playerHand[yourArrayIndex] = yourCard;
      }
      else
      {
         computerHand[yourArrayIndex] = yourCard;
      }
   }
   
   /**
   check
   To check if two card's ranks are equal
   @param playerCard the player's card
   @param computerCard the computer's card
   @return whether or not they're equal, 0 for player > computer, 1 for computer > player
   */
   public int check(Card playerCard, Card computerCard)
   {
      int toReturn = -1;
      
      // if the player's card rank is greater than the computer's card rank
      if(playerCard.getRank() > computerCard.getRank())
      {
   		// return 0 
         toReturn = 0;
      }
      else if(playerCard.getRank() < computerCard.getRank())
      {
   		//return 1
         toReturn = 1;
      }
      else
      {
         //return 3
         toReturn = 3;
      }
      
      return toReturn;
   }
   
   /**
   getHandSize
   Get the size of the hand
   @param playerOrComputer player or computer's hand?
   @return the size of the hand
   */
   public int getHandSize(int playerOrComputer)
   {
      int handSize = 0;
      if(playerOrComputer == 0)
      {
         for(int i=0;i<52;i++)
         {
            try {
               if(playerHand[i].getRank() > 0)
               {
                  handSize +=1;
               }
            }
            catch (NullPointerException e)
            {
            }
         }
      }
      else if(playerOrComputer == 1)
      {
         for(int i=0;i<52;i++)
         {
            try {
               if(computerHand[i].getRank() > 0)
               {
                  handSize +=1;
               }
            }
            catch (NullPointerException e)
            {
            }
         }
      }
      
      return handSize;
      
   }
   
   /**
   war
   Handles the war
   @param playerCard player card object
   @param computerCard computer card object
   @return war status
   */
   public int war(Card playerCard, Card computerCard)
   {
   
      // if player wins the war
      if(playerCard.getRank() > computerCard.getRank())
      {
         return 0;
      }
      else if(playerCard.getRank() < computerCard.getRank())
      {
         return 1;
      }
      else
      {
         return 3;
      }
   }
   
   /**
   Get the names of the top cards of each hand. This was for debugging.
   @return the names of the top cards of each hand
   */
   public String getTopHandCards()
   {
      String theString = "PlayerTopCard: " + playerHand[0].toString() + ", " + "ComputerTopCard: " + computerHand[0].toString();
      return theString;
   }
   
   /**
   addCards
   @param yourCardList the list of your cards
   @param playerOrComputer player or computer?
   */
   public void addCards(ArrayList<Card> yourCardList, int playerOrComputer) // 0 for player, 1 for computer
   {
      if(playerOrComputer == 0)
      {
         int tempLoc = 0;
         for (int i=0; i<52;i++)
         {
            if(this.getCardRankAtArrayIndex(i,playerOrComputer) == 0)
            {
               tempLoc = i;
               break;
            }
         }
         for (int i=0; i<yourCardList.size();i++)
         {
            this.setCard(tempLoc+i, yourCardList.get(i),playerOrComputer);
         }

      }
      else if(playerOrComputer == 1)
      {
         int tempLoc = 0;
         for (int i=0; i<52;i++)
         {
            if(this.getCardRankAtArrayIndex(i,playerOrComputer) == 0)
            {
               tempLoc = i;
               break;
            }
         }
         for (int i=0; i<yourCardList.size();i++)
         {
            this.setCard(tempLoc+i, yourCardList.get(i),playerOrComputer);
         }
      }
   }
   
   /** 
   checkWin
   Check if the game is over or not
   @return false if game is over, true if game isn't
   */
   public boolean checkWin()
   {
      boolean gameRunning = true; 
      
      if(playerHand.length == 0)
      {
         System.out.println("Computer wins the game!");
         gameRunning = false;
      }
      else if(computerHand.length == 0)
      {
         System.out.println("Player wins the game!");
         gameRunning = false;
      }
      
      return gameRunning;
   }
   
}