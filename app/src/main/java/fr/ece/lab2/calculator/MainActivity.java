package fr.ece.lab2.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    TextView textCalcul;
    TextView result;

    int param1 = 0;
    int param2 = 0;
    String operation = "undefined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCalcul = (TextView) findViewById(R.id.textCalcul);
        result = (TextView) findViewById(R.id.textResult);

       /* Button equal_btn = new Button(this);
        equal_btn.setTag("=");
        equal_btn.setLayout

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(
                new LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.FILL_PARENT)); */


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

        /*//If on appuie sur '=', l'opération est reset + le résultat s'affiche
        if (b.getTag().toString().equals("=")) {
            int res = Integer.parseInt(textCalcul.getText().toString()); //Une solution pour caster un textView en int
            result.setText(String.valueOf(res));
            textCalcul.setText("");
        }
        //If on appuie sur une touche, sa valeur s'écrit dans le text opération
        else {
            textCalcul.setText(textCalcul.getText() + b.getTag().toString());
            Toast.makeText(this, b.getTag().toString(),
                    Toast.LENGTH_LONG).show();
        }*/
    }

}
