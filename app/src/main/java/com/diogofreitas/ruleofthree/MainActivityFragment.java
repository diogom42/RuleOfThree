package com.diogofreitas.ruleofthree;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText[] input = new EditText[4];

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

        view.findViewById(R.id.buttonEqual).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ruleOfThree();
            }
        });

        view.findViewById(R.id.buttonCE).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CE();
            }
        });

//        Toast.makeText(getActivity(), "toast", Toast.LENGTH_SHORT).show();

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
        if (numberOfEmptyFields == 1) {
            //look for the oldest field
            for (int i = 0; i < 4; i++) {
                if (isEmpty(input[i])) {
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
                    //update the value of the empty EditText field
                    input[i].setText(Float.toString((mul1 * mul2) / div));
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
     * Clear all entries (all EditText fields)
     */
    public void CE() {
        for (int i = 0; i < 4; i++) {
            input[i].setText("");
        }
    }
}
