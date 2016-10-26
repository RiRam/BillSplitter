package com.riram.android.billsplitter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int tip = 0;
    double preTaxTotal = 0.0;
    double taxValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the tip value.
     */
    private void displayTip(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number + "%");
    }

    /**
     * Called when the + button is pressed - increment tip percentage.
     */
    public void increment(View view) {
        if (tip >= 0 && tip <= 100) {
            tip = tip + 1;
            displayTip(tip);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You can't calculate tip more than 100%.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * Called when the - button is pressed - decrements tip percentage.
     */
    public void decrement(View view) {
        if (tip > 0 && tip <= 100) {
            tip = tip - 1;
            displayTip(tip);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You can't tip a negative percent.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
