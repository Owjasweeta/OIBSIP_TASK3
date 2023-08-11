package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView res,sol;
    MaterialButton cleart,obracket,cbracket,aclear,dot;
    MaterialButton div,mul,sub,add,eq;
    MaterialButton button7,button8,button9,button4,button5,button6,button1,button2,button3,button0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.result);
        sol = findViewById(R.id.solution);
        assignId(cleart,R.id.clear_text);
        assignId(obracket,R.id.open_bracket);
        assignId(cbracket,R.id.close_bracket);
        assignId(aclear,R.id.all_clear);
        assignId(dot,R.id.decimal);
        assignId(div,R.id.divide);
        assignId(mul,R.id.multiply);
        assignId(sub,R.id.minus);
        assignId(add,R.id.plus);
        assignId(eq,R.id.equal);
        assignId(dot,R.id.decimal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
    }

        void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate = sol.getText().toString();

        if(buttonText.equals("AC")){
            sol.setText("");
            res.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            sol.setText(res.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        sol.setText(dataToCalculate);

        String finalResult=getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            res.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(("0")))
            {
                finalResult=finalResult.replace(".0","");

            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}