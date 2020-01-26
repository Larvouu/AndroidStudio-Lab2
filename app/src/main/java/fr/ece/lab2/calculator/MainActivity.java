package fr.ece.lab2.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    TextView textCalcul;
    TextView result;
    LinearLayout linearLayout;

    // param 1 pour le nombre 1, param 2 pour le nombre 2 de l'opération
    int param1 = 0;
    int param2 = 0;
    //Opérande
    String operation = "undefined";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_linear_layout);

        textCalcul = (TextView) findViewById(R.id.textCalcul);
        result = (TextView) findViewById(R.id.textResult);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutDigit);
/*
        //Déclaration du bouton =
        final Button equal_btn = new Button(this);
        equal_btn.setTag(R.string.equal);
        equal_btn.setText(R.string.equal);

        //équivalent à android:onclick="clickHandler"
        equal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickHandler(equal_btn);
            }
        });

        //Test de set des width et height du bouton =
        equal_btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //Ajout au linearLayout
        linearLayout.addView(equal_btn);
*/

    }

    public void clickHandler(View b) {


        //Si on appuie sur un chiffre
        if (!b.getTag().toString().equals("=") && //Syntaxe pour Not Equal avec un String
                !b.getTag().toString().equals("+") && !b.getTag().toString().equals("-") &&
                !b.getTag().toString().equals("*") && !b.getTag().toString().equals("/")) {
            //si un signe d'opération n'a pas encore été cliqué
            if (operation.equals("undefined")) {
                textCalcul.setText(textCalcul.getText() + b.getTag().toString());
                param1 = Integer.parseInt(textCalcul.getText().toString());
            }
            //si un signe d'opération a déjà été cliqué
            else {
                textCalcul.setText(textCalcul.getText() + b.getTag().toString());
                param2 = Integer.parseInt(textCalcul.getText().toString());
            }
        }

        //Si on appuie sur un signe d'opération
        else if (b.getTag().toString().equals("+") || b.getTag().toString().equals("-") ||
                b.getTag().toString().equals("*") || b.getTag().toString().equals("/")) {
            //On commence par reset le textView
            textCalcul.setText("");
            //switch pour savoir quelle opération est selectionée
            switch (b.getTag().toString()) {
                case "+":
                    operation = "+";
                    break;
                case "-":
                    operation = "-";
                    break;
                case "*":
                    operation = "*";
                    break;
                case "/":
                    operation = "/";
                    break;
            }
        }

        //Si on appuie sur le bouton "="
        else if (b.getTag().toString().equals("=")) {
            //On reset le textView
            textCalcul.setText("");
            //On effectue l'opération simple en fonction du signe opératoire
            int res = 0;
            switch (operation) {
                case "+":
                    res = param1 + param2;
                    break;
                case "-":
                    res = param1 - param2;
                    break;
                case "*":
                    res = param1 * param2;
                    break;
                case "/": //Blindage de la division par 0
                    if (param2 != 0) {
                        res = param1 / param2;
                    }
                    if (param2 == 0) {
                        Toast.makeText(this, "Division par 0 impossible", Toast.LENGTH_LONG).show();
                    }
                    break;
                case "undefined":
                    res = param1;
                    break;
            }
            //Puis on affiche le résultat
            if (param2 != 0) {
                result.setText(String.valueOf(res));
                //Vérification de l'opération avec un Toast
                Toast.makeText(this, param1 + " " + operation + " " + param2, Toast.LENGTH_LONG).show();
            }
            if (param2 == 0) {
                Toast.makeText(this, "Division par 0 impossible", Toast.LENGTH_LONG).show();
            }
            //On réinitialise les variables pour l'opération suivante
            param1 = 0;
            param2 = 0;
            operation = "undefined";
        }
    }
}
