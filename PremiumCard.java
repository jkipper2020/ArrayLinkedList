import java.util.Random;

/**
 * This is the premiumcard object which is a different type of card (extends the Card) and has its own constructors
 * and overrides the toString() method
 * @author Jonathan Kipper
 * @version 5/18/2020
 */
public class PremiumCard extends Card implements Comparable<Card>
{
    /**
     * This is the default constructor for the premiumCard object
     */
    public PremiumCard()
    {
        super();
    }

    /**
     * This constructor for the premiumcard object takes along an integer value and passes it along with the super() method
     * @param x an integer value
     */
    public PremiumCard(int x)
    {
        super(x);
    }

    /**
     * THis is the last constructor for the PremiumCard object and it takes along two integer values which refelct the card's
     * strength and toughness parameters
     * @param pow the value for the power of the card
     * @param tough the value for the toughness of the card
     */
    public PremiumCard(int pow, int tough)
    {
        super(pow, tough);
    }

    @Override
    public String toString()
    {
        return "{{" + getPower() + "/" + getToughness() + "::" + getCost() + "}}";
    }
}