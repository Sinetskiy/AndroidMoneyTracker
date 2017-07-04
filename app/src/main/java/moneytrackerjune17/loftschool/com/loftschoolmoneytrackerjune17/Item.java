package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

import java.io.Serializable;

/**
 * Created by andreysinetskiy on 29.06.17.
 */

public class Item implements Serializable {
    public static final String TYPE_EXPENSE = "expense";
    public static final String TYPE_INCOME = "income";

    int id = -1, price;
    String name, type;


    public Item(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
