import java.util.Arrays;


public class Assignment3
{

   public static void main(String[] args)
   {
      
      /* Hand tests to make sure it works */
      /*----------------------------------*/
      
      Card card1 = new Card('3', Card.Suit.clubs);
      Card card2 = new Card('T', Card.Suit.clubs);
      Card card3 = new Card('9', Card.Suit.hearts);
      Hand hand = new Hand();
      int counter = 0;
      
      // add max number of cards to hand
      for (int i = 0; i < Hand.MAX_CARDS; i++)
      {
         ++counter;
         switch(counter)
         {
            case 2:
               hand.takeCard(card2);
               break;
            case 3:
               hand.takeCard(card3);
               counter = 0;
               break;
            default:
               hand.takeCard(card1);
               break;
               
         }
      }
      
      // print out the entire hand
      System.out.println("Hand full");
      System.out.println("After deal:");
      System.out.println("Hand = " + hand.toString());
      System.out.println("");
      
      //examine card and show it
      System.out.println("Testing inspectCard():");
      Card testCard = hand.inspectCard(2);
      Card testCard2 = hand.inspectCard(101);
      System.out.println(testCard.toString());
      System.out.println(testCard2.toString());
      System.out.println("");
      
      // play every card in hand
      System.out.println("Play cards in Hand:");
      for(int i = hand.getNumCards(); i > 0; i--)
      {
         Card playedCard = hand.playCard();
         System.out.println(playedCard.toString());
      }
      
      // print out empty hand
      System.out.println("");
      System.out.println("After playing all cards:");
      System.out.println("Hand = " + hand.toString());
      
      /* end of hand test                 */
      /*----------------------------------*/
 
   }

}


/*------------------------------------------------------
 * Card Class
 *---------------------------------------------------- */
class Card
{
   public static enum Suit {clubs, diamonds, hearts, spades};
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


/*------------------------------------------------------
 * Hand Class
 *---------------------------------------------------- */
class Hand
{
   
   // max number of cards allowed in hand
   public static final int MAX_CARDS = 100;
   
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards;
   
   // default constructor
   public Hand()
   {
      this.numCards = 0;
   }  
   
   /*
    * resetHand takes no parameters and fills the array with null
    * then sets the numCards back to 0
    * */
   public void resetHand()
   {
      
      Arrays.fill(myCards, null);
      numCards = 0;
      
   }
   
   /*
    * takeCard takes a Card and adds it to hand
    * returns true if successful. (makes new copy of card)
    * */
   public boolean takeCard(Card card)
   {
    
      if(numCards < MAX_CARDS)
      {
         Card addCard = new Card(card.getValue(), card.getSuit());
         myCards[numCards] = addCard;
         numCards++;
         return true;
      }
      
      return false;
   }
   
   /*
    * playCard plays card on top of the deck
    * and returns that card to the caller
    * */
   public Card playCard()
   {
      
      if(numCards == 0)
         return null;
      
      Card card = new Card(myCards[numCards -1].getValue(),
            myCards[numCards -1].getSuit());
      myCards[numCards -1] = null;
      numCards--;
      
      return card;
      
   }
   
   /*
    * toString Displays the hand as a string
    * */
   public String toString() 
   {
      
      String handOfCards = "(";
      
      for(int i = 0; i < numCards; i++)
      {
         handOfCards += (i == numCards - 1) ? myCards[i].toString() : 
            myCards[i].toString() + ", ";
      }
      
      handOfCards += ")";
      
      return handOfCards;
      
   }

   /*
    * getNumCards returns the number of cards in the hand
    * */
   public int getNumCards()
   {
      
      return numCards;
      
   }
   
   /*
    * inspectCard returns the card asked for,
    * if the card is out of bounds it returns a card with
    * errorFlag true
    * */
   public Card inspectCard(int k)
   {
      
      if(k >= 0 && k < numCards)
         return myCards[k];
      
      // send a bad card so error flag is set
      return new Card('e', Card.Suit.clubs);
      
   }
   
}


/*------------------------------------------------------
 * Deck Class
 *---------------------------------------------------- */
class Deck
{


}
