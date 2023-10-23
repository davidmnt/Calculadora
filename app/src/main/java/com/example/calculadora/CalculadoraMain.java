package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        TextView pantalla = findViewById(R.id.pantalla);
        Button boton7 = findViewById(R.id.siete);
        Button boton8 = findViewById(R.id.ocho);
        Button boton9 = findViewById(R.id.nueve);
        Button botonDividir = findViewById(R.id.dividir);
        Button boton4 = findViewById(R.id.cuatro);
        Button boton5 = findViewById(R.id.cinco);
        Button boton6 = findViewById(R.id.seix);
        Button botonMulti = findViewById(R.id.multiplicar);
        Button boton1 = findViewById(R.id.uno);
        Button boton2 = findViewById(R.id.dos);
        Button boton3 = findViewById(R.id.tres);
        Button botonRestar = findViewById(R.id.resta);
        Button botonComa = findViewById(R.id.coma);
        Button boton0 = findViewById(R.id.cero);
        Button botonSuma = findViewById(R.id.suma);
        Button botonIgual = findViewById(R.id.igual);
        Button botonAC = findViewById(R.id.AC);
        TextView pantallaError = findViewById(R.id.pantallaError);


        //Declaramos un array de botones para no repetir codigo de esta forma podremos darle funcionalidad a los botones
        Button[] botones = {boton7, boton8, boton9, botonDividir, boton4, boton5, boton6, botonMulti, boton1, boton2, boton3, botonRestar, boton0, botonSuma, botonComa};

        for (int i = 0; i < botones.length; i++) {

            int cont = i;
            botones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quitarCero(pantalla);
                    String numero = botones[cont].getText().toString();
                    pantalla.setText(pantalla.getText() + numero);
                }
            });
        }

        botonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = 0;
                int contTotal = 0;
                char operacion = 0;
                String num1 = "";
                String num2 = "";

                //pasamos el numero a un Array de char
                char[] numChar = pantalla.getText().toString().toCharArray();

                //Hacemos un bucle para sacar el primer numero hasta el signo de operacion

                for (int i = 0; i < numChar.length; i++) {
                    //Forzamos un break para una vez llegado al signo pare de concatenar numeros al num1
                    if (numChar[i] == '+' || numChar[i] == '-' || numChar[i] == '*' || numChar[i] == '/') {
                        operacion = pantalla.getText().toString().charAt(contTotal);
                        break;
                    }
                    //Mientras el bucle no detecte un signo de operacion va a concatenar el numero y sumar el contador
                    if (numChar[i] != '+' || numChar[i] != '-' || numChar[i] != '*' || numChar[i] != '/') {
                        num1 += pantalla.getText().toString().charAt(i);
                        contTotal++;
                    }
                }
                Log.e("Numero1", num1);

                //Luego hacemos otro bucle para para sacra el num2 inicializando el cont del for anterior +1
                for (int i = contTotal + 1; i < numChar.length; i++) {
                    num2 += String.valueOf(pantalla.getText().toString().charAt(i));
                }
                Log.e("Numero2", num2);

                //En caso de que alguno de los numeros este vacio lanzaremos un error a la app

                if (num1.isEmpty() || num2.isEmpty()) {
                    Log.e("Error", "Numero 1 o numero 2 vacio");
                    pantallaError.setText("Error 404");
                    pantalla.setText("0");
                } else {


                    //Pasamos los numeros de String a int
                    double numero1 = Double.parseDouble(num1);
                    double numero2 = Double.parseDouble(num2);
                    //Metemos en una variable el signo, que lo sacamos gracias al contador, ya que forzamos con un break el bucle para que pare cuando
                    //llegue a un signo, lo que significa que si el numero1 es 12+ el contador sera igual a 2.


                    //Segun el signo hacemos la operacion correspondiente
                    switch (operacion) {
                        case '+':
                            result = numero1 + numero2;
                            break;
                        case '-':
                            result = numero1 - numero2;
                            break;
                        case '/':
                            result = numero1 / numero2;
                            break;
                        case '*':
                            result = numero1 * numero2;
                    }

                    //Pasamos el resultado double a String para pasarlo por pantalla
                    String resultado = "" + result;
                    Log.e("Resultado", resultado);

                    pantalla.setText(resultado);

                }
            }
        });

        botonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.setText("0");
            }
        });

    }

    //Creamos un metodo para quitar el 0 con el que empieza la calculadora.
    private static void quitarCero(TextView pantalla) {

        if (pantalla.getText().equals("0")) {
            pantalla.setText("");
        }
    }
}