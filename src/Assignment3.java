
public class Assignment3
{

   public static void main(String[] args)
   {
    

   }

}


class Card
{
   public enum Suit {clubs, diamonds, hearts, spades};
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   //constructor with parameters takes a card value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   //default constructor sets value to "A" and suit to spades
   public Card()
   {
      set('A', Suit.spades);
   }
   
   //public method to return value and suit as a string
   public String toString()
   {
      if (errorFlag == true)
         return("[invalid]");
      return String.valueOf(value) + " of " + suit;
   }
   
   //return true if card value is valid
   private boolean isValid(char value, Suit suit)
   {
      char[] cardValue = {'1', '2', '3', '4', '5', '6', '7', '8', '9',
                                  'T', 'A', 'J', 'K', 'Q'};
      for (int i = 0; i < cardValue.length; i++)
      {
         if (value == cardValue[i])
         {
            return true;
         }
      }
      return false;
   }
   
   //setter function 
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      else
      {
         errorFlag = true;
         return false;
      }
   }
   
   //accessor functions
   public char getValue()
   {
      return value;
   }
   
   public Suit getSuit()
   {
      return suit;
   }
   
   public boolean equals(Card card)
   {
      if (card == this)
         return true;
      return false;
   }
}


class Hand
{


}


class Deck
{


}
