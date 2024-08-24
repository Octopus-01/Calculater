package com.learn.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class MainActivity extends AppCompatActivity {
    private EditText InputTextField, OutputTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    private void initializeView() {
        InputTextField = findViewById(R.id.inputTextField);
        OutputTextField = findViewById(R.id.outputTextFeild);

    }

    @SuppressLint("SetTextI18n")
    public void ButtonClicked(View view)
   {
       Button button =(Button) view;
       String buttontext = button.getText().toString();
       String oldtext = InputTextField.getText().toString();

       if(buttontext.equalsIgnoreCase("C")){
           InputTextField.setText("");
       }
       else if (buttontext.equalsIgnoreCase("DEL"))
       {
           InputTextField.setText("");
           OutputTextField.setText("");
       } else if (buttontext.equalsIgnoreCase("=")) {

           // Calculate the equation
            if(oldtext.trim().equalsIgnoreCase("="))
            {
                InputTextField.setText(OutputTextField.getText().toString());
                OutputTextField.setText("");
                return;
            }

          try {
              Expression expression = new Expression(oldtext);
              EvaluationValue evaluate = expression.evaluate();
              OutputTextField.setText(evaluate.getStringValue());
              InputTextField.setText("");
          } catch (EvaluationException e) {
              Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
          } catch (ParseException e) {
              Toast.makeText(this, "Invalid Expression", Toast.LENGTH_SHORT).show();
          }


       } else{
           InputTextField.setText(oldtext+buttontext);
       }
   }
}