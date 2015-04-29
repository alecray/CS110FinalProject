import java.util.*;

public class Deck
{
   private Card[] deckArray = new Card[52];
   private Card[] playerHand = new Card[52];
   private Card[] computerHand = new Card[52];
   
   public Deck()
   {
      int currCard = 0; //to create the deck
      for(int i=0;i<=3;i++)
      {
         for(int j=2;j<15;j++)
         {
            deckArray[currCard] = new Card(i,j);
            currCard+=1;
         }
         
      }  
       
   }
   
   public void shuffle()
   {
      Collections.shuffle(Arrays.asList(deckArray));
   }
   
   public void deal()
   {
      for(int i=0;i<26;i++)
      {
         playerHand[i] = deckArray[i];
         computerHand[i] = deckArray[i+26];
      }
   }
   
   public Card drawCard(int playerOrComputer, int index) // pass in 0 for player, 1 for computer
   {
      int ZERO = 0;
      
      Card tempCard = new Card(0,0);
      
      if (playerOrComputer == ZERO)
      {
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
      else
      {
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
   

   public String toString()
   {
      String deckString = "";
      
      for(int i=0;i<52;i++)
      {
         deckString = deckString + deckArray[i].toString() + ", ";
      }
      
      return deckString;
   }
   
   public void shift(int times, int playerOrComputer) // 0 for player, 1 for computer 
   {
      Card[] tempCard = new Card[52];
      
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
   
   public int getCardRankAtArrayIndex(int yourArrayIndex, int playerOrComputer) // pass in 0 for player, 1 for computer
   {
      int ZERO = 0;
      
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
   To check if two card's ranks are equal
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
   
   public String getTopHandCards()
   {
      String theString = "PlayerTopCard: " + playerHand[0].toString() + ", " + "ComputerTopCard: " + computerHand[0].toString();
      return theString;
   }
   
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