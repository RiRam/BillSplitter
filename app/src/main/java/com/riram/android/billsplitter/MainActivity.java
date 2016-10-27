package com.riram.android.billsplitter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int tipPercent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the tip value in the tip TextView.
     */
    private void displayTip(int number) {
        TextView tipTextView = (TextView) findViewById(R.id.tip_text_view);
        tipTextView.setText("" + number + "%");
    }

    /**
     * Called when the + button is pressed -> increment tip percentage.
     */
    public void increment(View view) {
        if (tipPercent >= 0 && tipPercent <= 100) {
            tipPercent = tipPercent + 1;
            displayTip(tipPercent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You can't calculate tip more than 100%.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * Called when the - button is pressed -> decrements tip percentage.
     */
    public void decrement(View view) {
        if (tipPercent > 0 && tipPercent <= 100) {
            tipPercent = tipPercent - 1;
            displayTip(tipPercent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You can't tip a negative percent.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * This method displays the total value in the total TextView.
     */
    private void displayTotal(double number) {
        //TODO: force to show 2 decimals (currently does not if 0)
        TextView totalTextView = (TextView) findViewById(R.id.total);
        totalTextView.setText("$" + new DecimalFormat("#.##").format(number));
    }

    /**
     * Calculate the total cost including item costs, tax, and tip and
     * calls to display the total in a TextView next to the Calculate Button,
     * with a break down of the pre-tax total, tax, and tip in a short toast.
     */
    public void calculate(View v) {
        // calculate pre tax total
        double preTaxTotal = 0.0;

        EditText[] itemValues = {(EditText) findViewById(R.id.item_1_val),
                (EditText) findViewById(R.id.item_2_val),
                (EditText) findViewById(R.id.item_3_val),
                (EditText) findViewById(R.id.item_4_val),
                (EditText) findViewById(R.id.item_5_val)};

        for(EditText i : itemValues) {
            if(!i.getText().toString().trim().equals("")) {
                preTaxTotal += Double.parseDouble(i.getText().toString());
            }
        }

        // calculate tip
        double tip = preTaxTotal * (tipPercent / 100.0);

        // calculate tax
        double taxPercent = 0.0;
        if(!((EditText) findViewById(R.id.tax_input)).getText().toString().trim().equals("")) {
            // to avoid crashing if "Calculate" is pressed with no input
            taxPercent = Double.parseDouble(((EditText) findViewById(R.id.tax_input)).getText().toString());
        }
        double tax = preTaxTotal * (taxPercent / 100.0 );

        // sum
        double total = preTaxTotal + tip + tax;

        // breakdown toast
        Context context = getApplicationContext();
        CharSequence text = "Total: $" + new DecimalFormat("#.##").format(preTaxTotal) +
                " / Tip: $" + new DecimalFormat("#.##").format(tip) +
                " / Tax: $" + new DecimalFormat("#.##").format(tax);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // display
        displayTotal(total);
    }

    /**
     * Resets all fields to blank, and resets tip percentage to 0.
     */
    public void reset(View v) {
        // reset item values
        EditText[] itemValues = {(EditText) findViewById(R.id.item_1_val),
                (EditText) findViewById(R.id.item_2_val),
                (EditText) findViewById(R.id.item_3_val),
                (EditText) findViewById(R.id.item_4_val),
                (EditText) findViewById(R.id.item_5_val)};

        for(EditText i : itemValues) {
            i.setText("");
        }

        // reset tip
        tipPercent = 0;
        displayTip(tipPercent);

        // reset tax
        ((EditText) findViewById(R.id.tax_input)).setText("");

        // reset total
        ((TextView) findViewById(R.id.total)).setText("");
    }
}
