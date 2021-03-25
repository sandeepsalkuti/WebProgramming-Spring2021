package com.webproject.hiresh.pizzaorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int COFFEE_PRICE = 5;
    final int WHIPPED_CREAM_PRICE = 1;
    final int CHOCOLATE_PRICE = 2;
    final int OLIVES_PRICE = 2;
    final int TOMATOES_PRICE = 3;
    final int Jalapinoes = 1;
    final int Onions = 1;
    final int Olives = 1;
    final int Tomatoes = 1;
    final int Spinach = 1;
    final int Lettuce = 1;
    final int Bellpeppers = 1;
    String name = "";
    String order = "";
    String price1 = "";
    String quant = "";
    String totalmessage ="";
    int quantity = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_page);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();

        CheckBox olives = (CheckBox) findViewById(R.id.olives_checked);
        boolean hasOlives = olives.isChecked();

        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes_checked);
        boolean hasTomatoes = tomatoes.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasWhippedCream, hasChocolate,hasOlives,hasTomatoes);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate, hasOlives,hasTomatoes,totalPrice);
        Intent redirect = new Intent(HomePage.this, SummaryPage.class);
        redirect.putExtra("summary", orderSummaryMessage);
        redirect.putExtra("order", order);
        redirect.putExtra("price", price1);
        redirect.putExtra("quant", quant);
        redirect.putExtra("name", name);
        startActivity(redirect);
        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents

    }

    public void sendEmail(View view) {
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();

        CheckBox olives = (CheckBox) findViewById(R.id.olives_checked);
        boolean hasOlives = olives.isChecked();

        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes_checked);
        boolean hasTomatoes = tomatoes.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasWhippedCream, hasChocolate, hasOlives, hasTomatoes);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate,hasOlives,hasTomatoes, totalPrice);
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sswf7@umsystem.edu"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Pizza Order Summary");
        i.putExtra(Intent.EXTRA_TEXT   , orderSummaryMessage);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HomePage.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasWhippedCream, boolean hasChocolate,boolean hasOlives,boolean hasTomatoes, float price) {
        String orderSummaryMessage = "Name:"+userInputName + "\n" +
                "Add Onions? "+boolToString(hasWhippedCream) + "\n" +
                "Add Chicken? "+boolToString(hasChocolate) + "\n" +
                "Add Olives? "+boolToString(hasOlives) + "\n" +
                "Add Tomatoes? "+boolToString(hasTomatoes) + "\n" +
                "Quantity: "+quantity + "\n" +
                "Total: $ "+ price + "\n" +
                "Thank you for your order!";
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate,boolean hasOlives,boolean hasTomatoes) {
        int basePrice = COFFEE_PRICE;
        if (hasWhippedCream) {
            basePrice += WHIPPED_CREAM_PRICE;
        }
        if (hasChocolate) {
            basePrice += CHOCOLATE_PRICE;
        }
        if (hasOlives) {
            basePrice += OLIVES_PRICE;
        }
        if (hasTomatoes) {
            basePrice += TOMATOES_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}
