package com.example.jhonti.test_9_calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends ActionBarActivity {

    String first = "";
    String second = "";

    Button $9;
    Button $8;
    Button $7;
    Button $6;
    Button $5;
    Button $4;
    Button $3;
    Button $2;
    Button $1;
    Button $0;
    Button $point;

    Button times;
    Button plus;
    Button divide;
    Button minus;
    Button equals;
    Button cancel;

    EditText screen;

    Button selectedoperation = null;

    public void onTapped(View v)
    {
        switch(v.getId())
        {
            case R.id.buttonTimes:
                checkSelec(times);
                break;
            case R.id.buttonPlus:
                checkSelec(plus);
                break;
            case R.id.buttonMinus:
                checkSelec(minus);
                break;
            case R.id.buttonDivide:
                checkSelec(divide);
                break;
            case R.id.buttonEquals:
                doOperation(selectedoperation);
                break;
            case R.id.buttonCancel:
                first = "";
                second = "";
                if(selectedoperation != null)
                {
                    selectedoperation.setEnabled(true);
                }
                selectedoperation = null;
                setText(1);
                break;
            case R.id.button0:
                addToString("0");
                break;
            case R.id.button1:
                addToString("1");
                break;
            case R.id.button2:
                addToString("2");
                break;
            case R.id.button3:
                addToString("3");
                break;
            case R.id.button4:
                addToString("4");
                break;
            case R.id.button5:
                addToString("5");
                break;
            case R.id.button6:
                addToString("6");
                break;
            case R.id.button7:
                addToString("7");
                break;
            case R.id.button8:
                addToString("8");
                break;
            case R.id.button9:
                addToString("9");
                break;
            case R.id.buttonPoint:
                addToString(".");
        }
    }

    /*
    Checks if a a operation is already selected eg(+), deselects it and
    selects newer operation. Prevents user from selecting operation if
    first variable is not defined.
     */
    private void checkSelec(Button b)
    {
        if (first.equals(""))
        {
            return;
        }
        else if(selectedoperation != null)
        {
            selectedoperation.setEnabled(true);
        }

        b.setEnabled(false);
        selectedoperation = b;
    }


    /*
    When number is pressed, number is added to first or second
    string based on whether operation has been chosen yet.
     */
    private void addToString(String number)
    {
        if(selectedoperation == null)
        {
            first += number;
        }else
        {
            second += number;
        }

        if(selectedoperation == null)
        {
            setText(1);
        }else{
            setText(0);
        }
    }


    private void doOperation(Button selectedoperation)
    {
        if(this.second.equals("") || this.first.equals(""))
        {
            return;
        }
        double first = 0, second = 0, answer = 0;

        /*
        If "." is entered by itself as a variable it causes
        a numberformatexception.
         */
        try
        {
            first = Double.parseDouble(this.first);
            second = Double.parseDouble(this.second);
        }
        catch(Exception e)
        {
            return;
        }

        switch(selectedoperation.getId())
        {
            case R.id.buttonTimes:
                answer = first * second;
                break;
            case R.id.buttonPlus:
                answer = first + second;
                break;
            case R.id.buttonMinus:
                answer = first - second;
                break;
            case R.id.buttonDivide:
                answer = first / second;
                break;
        }
        this.selectedoperation.setEnabled(true);
        this.selectedoperation = null;
        this.first = removeDotZero(answer);
        this.second = "";

        setText(1);
    }

    /*
    Removes the ".0" which is automatically present
    when dealing with  doubles.
     */
    private String removeDotZero(double value)
    {
        String ans = String.valueOf(value);
        String[] ansAsArray = ans.split("");

        if(ansAsArray[ansAsArray.length - 1].equals("0") && ansAsArray[ansAsArray.length - 2].equals("."))
        {
            return ans.substring(0, ansAsArray.length - 3);
        }else
        {
            return ans;
        }
    }

    //Updates the EditText view
    private void setText(int i)
    {
        if(i == 1)
        {
            screen.setText(first);
        }
        else if (i == 0)
        {
            screen.setText(second);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("first", first);
        outState.putString("second", second);
        if(screen.getText().toString() != null)
        {
            outState.putString("screen", screen.getText().toString());
        }else
        {
            outState.putString("screen", "");
        }
        if(selectedoperation != null)
        {
            outState.putInt("selected", selectedoperation.getId());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        first = savedInstanceState.getString("first");
        second = savedInstanceState.getString("second");
        screen.setText(savedInstanceState.getString("screen"));

        //Checks if an operation (Button int variable) has been put in the bundle.
        try
        {
            ViewGroup parentView = (ViewGroup) findViewById(R.id.rel2);
            for (int i = 0; i < parentView.getChildCount(); i++)
            {
                View childView = parentView.getChildAt(i);
                int potButton = childView.getId();
                if (potButton == savedInstanceState.getInt("selected"))
                {
                    selectedoperation = (Button) childView;
                    break;
                }
            }
            selectedoperation.setEnabled(false);
        }
        catch(Exception e)
        {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setButtons();
    }

    private void setButtons()
    {
        $9 = (Button) findViewById(R.id.button9);
        $8 = (Button) findViewById(R.id.button8);
        $7 = (Button) findViewById(R.id.button7);
        $6 = (Button) findViewById(R.id.button6);
        $5 = (Button) findViewById(R.id.button5);
        $4 = (Button) findViewById(R.id.button4);
        $3 = (Button) findViewById(R.id.button3);
        $2 = (Button) findViewById(R.id.button2);
        $1 = (Button) findViewById(R.id.button1);
        $0 = (Button) findViewById(R.id.button0);
        $point = (Button) findViewById(R.id.buttonPoint);
        times = (Button) findViewById(R.id.buttonTimes);
        plus = (Button) findViewById(R.id.buttonPlus);
        divide = (Button) findViewById(R.id.buttonDivide);
        minus = (Button) findViewById(R.id.buttonMinus);
        equals = (Button) findViewById(R.id.buttonEquals);
        cancel = (Button) findViewById(R.id.buttonCancel);

        screen = (EditText) findViewById(R.id.screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
