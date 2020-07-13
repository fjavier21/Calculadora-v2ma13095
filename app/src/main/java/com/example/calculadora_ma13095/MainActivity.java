package com.example.calculadora_ma13095;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        pantalla1 =findViewById(R.id.pantalla1);
        pantalla2 =findViewById(R.id.pantalla2);
    }

    public static String numeroWidget = "";
    public static String numeroWidget2 = "";


    DecimalFormat formato = new DecimalFormat("0.0000");//formato de decimales
    private TextView pantalla1;


    private TextView pantalla2;


    String texto = "", texto2="";

    boolean Sipunto=false,error=false,estadoBotones=true;

    int numero = 0;

    double NumA = 0, NumB = 0;
    char op;

    public void tecla(View v) {
        if (numero <= 11) {
            switch (v.getId()) {
                case R.id.bot0:
                      texto = texto + "0";

                        break;
                case R.id.bot1:

                      texto = texto + "1";

                      break;

                case R.id.bot2:

                      texto = texto + "2";
                      break;
                case R.id.bot3:
                    texto = texto + "3";
                    break;
                case R.id.bot4:
                    texto = texto + "4";
                    break;
                case R.id.bot5:
                    texto = texto + "5";
                    break;
                case R.id.bot6:

                    texto = texto + "6";

                    break;

                case R.id.bot7:

                       texto = texto + "7";
                       break;
                case R.id.bot8:
                    texto = texto + "8";
                    break;
                case R.id.bot9:
                    texto = texto + "9";
                    break;



                default:

                    break;
            }
            numero++;
        }else{
            if(Sipunto){
                Toast.makeText(getApplicationContext(),"Longitud máxima",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Longitud máxima",Toast.LENGTH_SHORT).show();
            }
        }
        ActualizarNumero(1);
        pantalla1.setText(texto);
    }





    public void borrar(View v) {//3 tipos de borrado

        switch (v.getId()) {

            case R.id.botBorrar://de 1 en 1


                if(!texto.equals("")){


                    if (((numero > 0) || Sipunto) && ((texto.charAt(texto.length() - 1)) == '.')){



                        Sipunto = false;

                        texto = texto.substring(0, texto.length() - 1);

                        numero = texto.length();
                    }



                    else {
                        texto = texto.substring(0, texto.length() - 1);
                        numero = numero - 1;
                    }
                }

                break;

            case R.id.botc:

                texto = "";

                texto2 = "";
                numero = 0;

                NumA = 0;
                NumB = 0;
                Sipunto=false;

                ActualizarNumero(2);
                pantalla2.setText("");
                break;
        }
        ActualizarNumero(1);
        pantalla1.setText(texto);

    }

    public void punto(View v) {
        if (!Sipunto) {
            if (numero == 0) {

                texto = texto + "0.";


                Sipunto = true;
            } else {
                texto = texto + ".";
                Sipunto = true;
            }
            numero = 0;
        }
        ActualizarNumero(1);
        pantalla1.setText(texto);
    }

    public void operacion(View v){


        if(texto.equals("")){

            if(!pantalla2.getText().equals("")){

                texto2 = texto2.substring(0, texto2.length() - 1);

                switch (v.getId()) {

                    case R.id.botdivision:

                        texto2 = texto2 + "/";
                        break;
                    case R.id.botproducto:
                        texto2 = texto2 + "*";
                        break;
                    case R.id.botM:

                        texto2 = texto2 + "+";
                        break;
                    case R.id.botmenos:
                        texto2 = texto2 + "-";
                        break;
                }

                ActualizarNumero(2);
                pantalla2.setText(texto2);
            }

        }else{

            if(pantalla2.getText().equals("")){


                switch (v.getId()) {

                    case R.id.botdivision:

                        texto2 = texto + "/";
                        break;
                    case R.id.botproducto:

                        texto2 = texto + "*";
                        break;
                    case R.id.botM:
                        texto2 = texto + "+";
                        break;
                    case R.id.botmenos:
                        texto2 = texto + "-";
                        break;
                }
                NumA = Double.parseDouble(texto);

            }else{

                op = pantalla2.getText().charAt(pantalla2.getText().length()-1);


                NumB = Double.parseDouble(texto);
                switch (op){
                    case '/':
                        if(NumB==0){
                            error=true;
                            texto2="";
                            NumA=0;
                            NumB=0;
                            Sipunto=false;
                        }else{


                            NumA=NumA/NumB;
                        }
                        break;
                    case '*':
                        NumA=NumA*NumB;
                        break;
                    case '+':
                        NumA=NumA+NumB;
                        break;
                    case '-':
                        NumA=NumA-NumB;
                        break;
                }
                if(!error){
                    texto2 = "" + NumA;
                    switch (v.getId()) {
                        case R.id.botdivision:
                            texto2 = texto2 + "/";
                            break;
                        case R.id.botproducto:
                            texto2 = texto2 + "*";
                            break;
                        case R.id.botM:
                            texto2 = texto2 + "+";



                            break;
                        case R.id.botmenos:


                            texto2 = texto2 + "-";
                            break;
                    }
                }
            }
            texto = "0";
            ActualizarNumero (1);
            ActualizarNumero(2);
            error=false;
            numero=0;
            Sipunto=false;
            pantalla1.setText(texto);
            pantalla2.setText(texto2);
        }
    }



    public void Igual(View v){
        if((!texto.equals(""))&&(!texto2.equals(""))){
            op = pantalla2.getText().charAt(pantalla2.getText().length()-1);
            NumB = Double.parseDouble(texto);
            switch (op){
                case '/':
                    if(NumB==0){
                        error=true;
                        Toast.makeText(getApplicationContext(),"No se puede dividir",Toast.LENGTH_SHORT).show();
                        texto="";
                        texto2="";
                        NumA=0;
                        NumB=0;
                        Sipunto=false;
                    }else{
                        NumA=NumA/NumB;
                    }
                    break;
                case '*':
                    NumA=NumA*NumB;
                    break;
                case '+':
                    NumA=NumA+NumB;
                    break;
                case '-':
                    NumA=NumA-NumB;
                    break;
            }
            if(!error){
                validar(NumA);
                texto2 = "";
                texto = "" + NumA;
            }
            ActualizarNumero(1);
            ActualizarNumero(2);
            error=false;
            pantalla1.setText(texto);
            pantalla2.setText(texto2);
            for (int a=0; a<texto.length(); a++){
                op = texto.charAt(a);
                if(op=='.'){
                    Sipunto=true;
                }
            }
        }

    }

    public void cuadrado(View v){
        try {


            NumA = Double.parseDouble(texto);
            NumA = Math.pow(NumA, 2);
        }

        catch (NumberFormatException e){

            e.printStackTrace();
            ActualizarNumero(1);
            ActualizarNumero(2);
            error = false;


        }
        pantalla1.setText(""+formato.format(NumA));
        pantalla2.setText("("+texto+")²="+formato.format(NumA));

    }

    public void raiz(View v){
try {


    NumA = Double.parseDouble(texto);

    if (NumA < 0) {
        NumA = NumA * -1;
        NumA = Math.sqrt(NumA);
        ActualizarNumero(1);
        ActualizarNumero(2);
        error = false;
        pantalla1.setText("");
        pantalla2.setText("√(" + texto + ")=" + formato.format(NumA) + "i");


        texto = "";
        texto2 = "";
        numero = 0;
        NumA = 0;
        NumB = 0;
        Sipunto = false;
        ActualizarNumero(1);
        ActualizarNumero(2);
    } else {


        NumA = Math.sqrt(NumA);
        ActualizarNumero(1);
        ActualizarNumero(2);
        error = false;
        pantalla1.setText("" + formato.format(NumA));


        pantalla2.setText("√" + texto + "=" + formato.format(NumA));

       // pantalla2.setText();
    }
}
catch (NumberFormatException e){
    e.printStackTrace();

}

}

    public void off (View v){
        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
        finish();
    }




    public void Signo(View v){
        if(!texto.equals("")){
            texto= "" + (Double.parseDouble(texto)*(-1));
            pantalla1.setText(texto);
            ActualizarNumero(1);
        }
    }

    public void validar (double num){
        double numlimit1=999999999999.999999999999;
        double numlimit2=0.999999999999;
        double decimal;
        decimal= num - (int)num;
        if(num>numlimit1||decimal>numlimit2){
            numero=12;
        }
    }

    public void ActualizarNumero (int opcion){
        switch (opcion){
            case 1:
                numeroWidget=texto;
                break;
            case 2:
                numeroWidget2=texto2;
                break;
        }
    }

    public void bloqueo(View v) {

        if (estadoBotones) {
            //operaciones
            View suma = findViewById(R.id.botM);
            suma.setEnabled(false);

            View resta = findViewById(R.id.botmenos);
            resta.setEnabled(false);
            View multiplicacion = findViewById(R.id.botproducto);
            multiplicacion.setEnabled(false);
            View division = findViewById(R.id.botdivision);
            division.setEnabled(false);

            //teclado numerico
            View cero = findViewById(R.id.bot0);
            cero.setEnabled(false);
            View uno = findViewById(R.id.bot1);
            uno.setEnabled(false);
            View dos = findViewById(R.id.bot2);
            dos.setEnabled(false);
            View tres = findViewById(R.id.bot3);
            tres.setEnabled(false);
            View cuatro = findViewById(R.id.bot4);
            cuatro.setEnabled(false);
            View cinco = findViewById(R.id.bot5);
            cinco.setEnabled(false);
            View seis = findViewById(R.id.bot6);
            seis.setEnabled(false);
            View siete = findViewById(R.id.bot7);
            siete.setEnabled(false);
            View ocho = findViewById(R.id.bot8);
            ocho.setEnabled(false);
            View nueve = findViewById(R.id.bot9);
            nueve.setEnabled(false);

            //demas operaciones
            View ce = findViewById(R.id.botc);
            ce.setEnabled(false);


            View borrador = findViewById(R.id.botBorrar);
            borrador.setEnabled(false);
            View raiz = findViewById(R.id.botraiz);
            raiz.setEnabled(false);
            View cuadrado = findViewById(R.id.botpotencia);
            cuadrado.setEnabled(false);
            View punto = findViewById(R.id.botpunto);
            punto.setEnabled(false);
            View igual = findViewById(R.id.igual);
            igual.setEnabled(false);
            View masMenos = findViewById(R.id.botsigno);
            masMenos.setEnabled(false);

            Toast.makeText(getApplicationContext(), "Bloqueado ", Toast.LENGTH_SHORT).show();
            estadoBotones = false;
        } else {


            View suma = findViewById(R.id.botM);
            suma.setEnabled(true);
            View resta = findViewById(R.id.botmenos);
            resta.setEnabled(true);
            View multiplicacion = findViewById(R.id.botproducto);
            multiplicacion.setEnabled(true);
            View division = findViewById(R.id.botdivision);
            division.setEnabled(true);

            //teclado
            View cero = findViewById(R.id.bot0);
            cero.setEnabled(true);
            View uno = findViewById(R.id.bot1);
            uno.setEnabled(true);
            View dos = findViewById(R.id.bot2);
            dos.setEnabled(true);
            View tres = findViewById(R.id.bot3);
            tres.setEnabled(true);
            View cuatro = findViewById(R.id.bot4);
            cuatro.setEnabled(true);
            View cinco = findViewById(R.id.bot5);
            cinco.setEnabled(true);
            View seis = findViewById(R.id.bot6);
            seis.setEnabled(true);
            View siete = findViewById(R.id.bot7);
            siete.setEnabled(true);
            View ocho = findViewById(R.id.bot8);
            ocho.setEnabled(true);
            View nueve = findViewById(R.id.bot9);
            nueve.setEnabled(true);

            //OPERACIONES
            View ce = findViewById(R.id.botc);
            ce.setEnabled(true);

            View c = findViewById(R.id.botc);
            c.setEnabled(true);
            View borrador = findViewById(R.id.botBorrar);
            borrador.setEnabled(true);
            View raiz = findViewById(R.id.botraiz);
            raiz.setEnabled(true);
            View cuadrado = findViewById(R.id.botpotencia);
            cuadrado.setEnabled(true);

            View punto = findViewById(R.id.botpunto);
            punto.setEnabled(true);
            View igual = findViewById(R.id.igual);
            igual.setEnabled(true);
            View masMenos = findViewById(R.id.botsigno);
            masMenos.setEnabled(true);

            Toast.makeText(getApplicationContext(), "Desbloqueado", Toast.LENGTH_SHORT).show();
            estadoBotones = true;
        }
    }}