package com.diogofreitas.ruleofthree;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText[] input = new EditText[4];
    Date[] inputTimes = new Date[4]; // gets the input times of each field

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // initialize input with each correspondent EditText field
        input[0]   = view.findViewById(R.id.input0);
        input[1]   = view.findViewById(R.id.input1);
        input[2]   = view.findViewById(R.id.input2);
        input[3]   = view.findViewById(R.id.input3);

        // initialize inputTimes with current Dates (we need Date values to decide which field is older)
        for (int i = 0; i < 4; i++) {
            inputTimes[i] = new Date();
        }

//        Toast.makeText(getActivity(), "toast", Toast.LENGTH_SHORT).show();

        // add clicklistener to inputs
        input[0].addTextChangedListener(
                new TextWatcher()
                {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(count == 1 && !isEmpty(input[0])) {
                            inputTimes[0] = new Date();
                            ruleOfThree();
                        } else {
                            inputTimes[0] = new Date(0);
                        }
                    }
                    public void afterTextChanged(Editable s) {

                    }
                });
        input[1].addTextChangedListener(
                new TextWatcher()
                {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(count == 1 && !isEmpty(input[1])) {
                            inputTimes[1] = new Date();
                            ruleOfThree();
                        } else {
                            inputTimes[1] = new Date(0);
                        }
                    }
                    public void afterTextChanged(Editable s) {

                    }
                });
        input[2].addTextChangedListener(
                new TextWatcher()
                {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(count == 1 && !isEmpty(input[2])) {
                            inputTimes[2] = new Date();
                            ruleOfThree();
                        } else {
                            inputTimes[2] = new Date(0);
                        }
                    }
                    public void afterTextChanged(Editable s) {

                    }
                });
        input[3].addTextChangedListener(
                new TextWatcher()
                {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(count == 1 && !isEmpty(input[3])) {
                            inputTimes[3] = new Date();
                            ruleOfThree();
                        } else {
                            inputTimes[3] = new Date(0);
                        }
                    }
                    public void afterTextChanged(Editable s) {

                    }
                });

        return view;
    }

    /**
     * Calculate the rule of Three if enough fields are filed out
     */
    public void ruleOfThree() {
        //check the number of empty fields
        int numberOfEmptyFields = 0;
        for (int i = 0; i < 4; i++) {
            if (isEmpty(input[i])) {
                numberOfEmptyFields++;
            }
        }

        //if there are enough fields filled, we can compute the Rule of Three at the oldest one
        if (numberOfEmptyFields <= 1) {
            //look for the oldest field
            for (int i = 0; i < 4; i++) {
                if (isOldest(i)) {
                    float mul1, mul2, div;
                    //decides how to calculate
                    switch (i) {
                        case 0:
                            mul1 = Float.valueOf(input[1].getText().toString());
                            mul2 = Float.valueOf(input[2].getText().toString());
                            div = Float.valueOf(input[3].getText().toString());
                            break;
                        case 1:
                            mul1 = Float.valueOf(input[0].getText().toString());
                            mul2 = Float.valueOf(input[3].getText().toString());
                            div = Float.valueOf(input[2].getText().toString());
                            break;
                        case 2:
                            mul1 = Float.valueOf(input[0].getText().toString());
                            mul2 = Float.valueOf(input[3].getText().toString());
                            div = Float.valueOf(input[1].getText().toString());
                            break;
                        case 3:
                            mul1 = Float.valueOf(input[1].getText().toString());
                            mul2 = Float.valueOf(input[2].getText().toString());
                            div = Float.valueOf(input[0].getText().toString());
                            break;
                        default:
                            mul1 = 1;
                            mul2 = 1;
                            div = 1;
                    }
                    //update the value of the oldest EditText field
                    input[i].setText(Float.toString((mul1 * mul2) / div));
                    //update time of update to the correspondent field
                    inputTimes[i] = new Date();
                    break;
                }
            }
        }
    }

    /**
     * Check if a Field is empty
     * @param tf is the EditText field that will be checked
     * @return returns true ith tf is empty and false if it's not
     */
    public boolean isEmpty(EditText tf) {
        if (tf.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the field of index k is the oldest one
     * @param k is the index of the EditText field (from 0 to 3)
     * @return returns true if k is the oldest EditText field and false if it's not
     */
    public boolean isOldest(int k) {
        for (int i = 1; i < 4; i++) {
            if (inputTimes[k].after(inputTimes[(i + k) % 4])) {
                return false;
            }
        }
        return true;
    }
}
