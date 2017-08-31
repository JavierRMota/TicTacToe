package com.jrmota.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView t;
    private int counter = 0;
    private List<View> jugadas = new ArrayList<>();
    String [][] juego = new String[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t =(TextView) findViewById(R.id.estado);
    }


    public void play(View v)
    {
        ImageView ficha = (ImageView) v;
        for(View vista : jugadas)
        {
            if(vista.getId()==v.getId())
            {
                return;
            }
        }
        jugadas.add(v);
        if(counter%2==0)
        {
            ficha.setImageDrawable(getDrawable(R.drawable.black));
            setJuego("j1",v);
            t.setText("Turno Jugador 2");
        }
        else
        {
            ficha.setImageDrawable(getDrawable(R.drawable.color));
            setJuego("j2",v);
            t.setText("Turno Jugador 1");

        }
        counter++;
        if(counter==9 && !gano()) clean();
        if(gano()) clean();
    }
    public void clean()
    {
        t.setText("Turno Jugador 1");
        for(Object v : jugadas.toArray())
        {
            ImageView j =(ImageView) v;
            j.setImageDrawable(getDrawable(R.drawable.white));
        }
        jugadas.clear();
        counter=0;
        for (int x = 0; x<3; x++)
        {
            for(int k = 0; k<3; k++)
            {
                juego[x][k]=null;
            }
        }
    }
    public boolean gano()
    {
        String jugador;
        if(counter%2==1) jugador= "j1";
        else jugador="j2";
        if(juego[0][0]==jugador && juego[0][1]==jugador && juego[0][2]==jugador
                ||
                juego[1][0]==jugador && juego[1][1]==jugador && juego[1][2]==jugador
                ||
                juego[2][0]==jugador && juego[2][1]==jugador && juego[2][2]==jugador
                ||
                juego[0][0]==jugador && juego[1][0]==jugador && juego[2][0]==jugador
                ||
                juego[0][1]==jugador && juego[1][1]==jugador && juego[2][1]==jugador
                ||
                juego[0][2]==jugador && juego[1][2]==jugador && juego[2][2]==jugador
                ||
                juego[0][0]==jugador && juego[1][1]==jugador && juego[2][2]==jugador
                ||
                juego[0][2]==jugador && juego[1][1]==jugador && juego[2][0]==jugador)
        {
            TextView textView;
            if(jugador=="j1")
                textView= (TextView) findViewById(R.id.j1);
            else
                textView= (TextView) findViewById(R.id.j2);
            int sum = Integer.parseInt(textView.getText().toString())+1;
            textView.setText(sum+"");
            return true;
        }
        return false;
    }
    public void setJuego(String jugador, View v)
    {
        if(v.getId() == R.id.up_left)
            juego[0][0] = jugador;
        if(v.getId() == R.id.up_center)
            juego[0][1] = jugador;
        if(v.getId() == R.id.up_right)
            juego[0][2] = jugador;
        if(v.getId() == R.id.center_left)
            juego[1][0] = jugador;
        if(v.getId() == R.id.center_center)
            juego[1][1] = jugador;
        if(v.getId() == R.id.center_right)
            juego[1][2] = jugador;
        if(v.getId() == R.id.down_left)
            juego[2][0] = jugador;
        if(v.getId() == R.id.down_center)
            juego[2][1] = jugador;
        if(v.getId() == R.id.down_right)
            juego[2][2] = jugador;

    }
}
