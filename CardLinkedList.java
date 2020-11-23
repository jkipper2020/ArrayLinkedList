import java.util.Random;

/**
 * @author Jonathan Kipper
 * @version 5/27/2020
 */
public class CardLinkedList implements CardList
{
    private class Node<Card>
    {
        public Card data;
        public Node<Card> next;
    }

    private Node<Card> head;
    private int sizeCounter;

    public CardLinkedList()
    {
        head = null;
        sizeCounter = 0;
    }

    @Override
    public int size()
    {
        return sizeCounter;
    }

    @Override
    public void add(Card c)
    {
        if (head == null) //if the list is empty it makes the first node and fills it
        {
            head = new Node<>();
            head.data = c;
            head.next = null;
            sizeCounter++;
        }
        else //finds the last spot in the list
        {
            Node<Card> current = head;

            while (current.next != null)
            {
                current = current.next;
            }

            current.next = new Node<>();
            current.next.data = c;
            current.next.next = null;
            sizeCounter++;
        }
    }

    @Override
    public String toString()
    {
        if (sizeCounter == 0)
        {
            return "[0: :0]";
        }
        else
        {
            StringBuilder val = new StringBuilder();

            Node<Card> current = head;

            while (current != null)
            {
                val.append(",").append(current.data);
                current = current.next;
            }

            val = new StringBuilder(val.substring(1));
            return "[0: " + val + " :" + sizeCounter + "]";
        }
    }

    @Override
    public void add(int loc, Card c)
    {
        if (loc < 0)
        {
            throw new IllegalArgumentException("Index less than zero");
        }
        else if (loc > sizeCounter)
        {
            throw new IllegalArgumentException("Index greater than list size");
        }
        else if (loc == sizeCounter)
        {
            add(c);
        }
        else if (loc == 0)
        {
            Node<Card> tmp = new Node<>();
            tmp.data = c;
            tmp.next = head;
            head = tmp;
            sizeCounter++;
        }
        else
        {
            Node<Card> current = head;
            for (int i = 1; i < loc; i++)
            {
                current = current.next;
            }

            Node<Card> tmp = new Node<>();
            tmp.data = c;

            tmp.next = current.next;
            current.next = tmp;
            sizeCounter++;
        }
    }

    @Override
    public Card remove()
    {
        if (head == null)
        {
            return null;
        }

        Node<Card> current = head;
        Node<Card> behind = head;

        while (current.next != null)
        {
            behind = current;
            current = current.next;
        }

        Card c = current.data;
        behind.next = current.next;

        sizeCounter--;
        return c;
    }

    @Override
    public Card remove(int loc)
    {
        if (loc < 0)
        {
            throw new IllegalArgumentException("Index less than zero");
        }
        else if (loc > sizeCounter)
        {
            throw new IllegalArgumentException("Index greater than list size");
        }
        else if (loc == 0)
        {
            Card c = head.data;
            head = head.next;
            sizeCounter--;
            return c;
        }
        else
        {
            Node<Card> current = head;
            Node<Card> behind = head;

            for (int i = 0; i < loc; i++)
            {
                behind = current;
                current = current.next;
            }

            Card c = current.data;
            behind.next = current.next;
            sizeCounter--;
            return c;
        }
    }

    @Override
    public Card get(int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("Index less than zero");
        }
        else if (i > sizeCounter)
        {
            throw new IllegalArgumentException("Index greater than list size");
        }
        else if (i == 0)
        {
            return head.data;
        }
        else
        {
            Node<Card> current = head;

            for (int j = 0; j < i; j++)
            {
                current = current.next;
            }

            return current.data;
        }
    }

    @Override
    public int indexOf(Card c)
    {
        int index = 0;
        Node<Card> current = head;

        while (current != null)
        {
            if (current.data.equals(c))
            {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public void sort()
    {

    }

    @Override
    public void shuffle()
    {
        if (head == null)
        {
            return;
        }
        else
        {
            Random myRandom = new Random();


            //swap(0,2);

            for (int i = 0; i < sizeCounter * 5; i++)
            {
                int lower = myRandom.nextInt(sizeCounter);
                int upper = myRandom.nextInt(sizeCounter);

                //System.out.println("Low: " + lower + ",   Upper: " + upper);

                swap(lower, upper);

            }
        }
    }

    private void swap(int A, int B)
    {
        Node<Card> aCurrent = head;
        Node<Card> bCurrent = head;

        Node<Card> aTemp = head;
        Node<Card> bTemp = head;


        for (int i = 0; i < A; i++)
        {
            aTemp = aCurrent;
            aCurrent = aCurrent.next;
        }

        for (int i = 0; i < B; i++)
        {
            bTemp = bCurrent;
            bCurrent = bCurrent.next;
        }

        Node<Card> x = new Node<>();
        x.data = bCurrent.data;

        bTemp.next.data = aCurrent.data;
        aTemp.next.data = x.data;
    }

    @Override
    public void clear()
    {
        head = null;
        sizeCounter = 0;
    }
}