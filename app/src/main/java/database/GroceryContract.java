package database;
import android.provider.BaseColumns;

public class GroceryContract {

    /*it will only inherit the id column from the BaseColumn */
    public static final class GroceryEntry implements BaseColumns {
        public static final String TABLE_NAME = "groceryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
