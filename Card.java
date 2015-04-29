public class Card
{
   
   public static int SPADES = 0;
   public static int CLUBS = 1;
   public static int HEARTS = 2;
   public static int DIAMONDS = 3;
   
   public static int ACE = 14;
   public static int JACK = 11;
   public static int QUEEN = 12;
   public static int KING = 13;
   
   private int rank;
   private int suit;
   
   /**
   Constructor
   @param yourSuit the suit of your card (spades = 0, clubs = 1, hearts = 2, diamonds = 3)
   @param yourRank the rank of your card from 1-14
   */
   public Card(int yourSuit, int yourRank)
   {
      suit = yourSuit;
      rank = yourRank;
   }
   
   /**
   @return the suit of your card
   */
   public int getSuit()
   {
      return suit;
   }
   
   /**
   @return the rank of your card
   */
   public int getRank()
   {
      return rank;
   }
   
   /**
   @return the string of your card
   */
   public String toString()
   {  
      String suitString;
      String rankString = "";
      
      if(suit == SPADES){suitString = "s";}
      else if(suit == CLUBS){suitString = "c";}
      else if(suit == HEARTS){suitString = "h";}
      else if(suit == DIAMONDS){suitString = "d";}
      else{suitString = "Invalid";}
      
      if(rank == ACE){rankString = "ace";}
      if(rank == JACK){rankString = "jack";}
      if(rank == QUEEN){rankString = "queen";}
      if(rank == KING){rankString = "king";}
      
      if(rankString != "")
      {
         String totalString = rankString + suitString;
         return totalString;
      }
      else
      {
         String totalString = rank + suitString;
         return totalString;
      }
   }
   
   /**
   @param otherCard the other card object
   @return whether or not the cards have the same suit
   */
   public boolean equals(Card otherCard)
   {
      if(suit == otherCard.getSuit())
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}
      