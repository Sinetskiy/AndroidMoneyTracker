package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

/**
 * Created by andreysinetskiy on 29.06.17.
 */

public class Item {
    public static final String TYPE_EXPENSE = "expense";
    public static final String TYPE_INCOME = "income";

    int id;
    String name;
    int price;
    String type;


    public Item(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
