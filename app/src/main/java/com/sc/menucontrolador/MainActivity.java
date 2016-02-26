package com.sc.menucontrolador;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView [] textViews;
    private Button [] buttons;
    private RelativeLayout container;
    private SeekBar fuente;
    float [] sizeTextView;
    LinearLayout menu_fuente;
    private String[] valores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadView();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void loadView() {
        container = (RelativeLayout) findViewById(R.id.container);
        fuente = (SeekBar) findViewById(R.id.seekBar);


        textViews = new TextView[3];
        textViews[0] = (TextView) findViewById(R.id.textView4);
        textViews[1] = (TextView) findViewById(R.id.textView2);
        textViews[2] = (TextView) findViewById(R.id.textView3);

        sizeTextView = new float[textViews.length];
        sizeTextView[0] = textViews[0].getTextSize()-10;
        sizeTextView[1] = textViews[1].getTextSize()-10;
        sizeTextView[2] = textViews[2].getTextSize()-10;

        buttons = new Button[3];
        buttons[0] = (Button) findViewById(R.id.btn_white);
        buttons[1] = (Button) findViewById(R.id.btn_sepia);
        buttons[2] = (Button) findViewById(R.id.btn_noche);

        fuente.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeFontSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].setOnClickListener(this);
        }


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        valores = getResources().getStringArray(R.array.typeFonts);
        String[] typeFonts = getResources().getStringArray(R.array.resourseTypeFonts);
        menu_fuente = (LinearLayout) findViewById(R.id.menu_fuente);
        for(int i=0;i<valores.length;i++)
        {
            Typeface faceFont = Typeface.createFromAsset(getAssets(), typeFonts[i]);
            final TextView textArial = new TextView(this);
            textArial.setText(valores[i]);
            textArial.setTextSize(26);
            textArial.setGravity(Gravity.RIGHT);
           // textArial.setTextAppearance(this, R.style.styleFontTace);

            textArial.setTypeface(faceFont);
            menu_fuente.addView(textArial);
            textArial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFontFace(textArial.getTypeface());
                }
            });
        }
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
                //changeFontFace(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              }
        });

    }


    @Override
    public void onClick(View v) {
        int seleccion = 0;
        switch (v.getId())
        {
            case R.id.btn_white:
                seleccion=0;
                break;

            case R.id.btn_sepia:
                seleccion=1;
                break;

            case R.id.btn_noche:
                seleccion=2;
                break;
        }
        cambiarFondo(seleccion);
    }

    public void cambiarFondo(int seleccion)
    {
        int color_text = 0;
        int color_background = 0;
        switch (seleccion)
        {
            case 0:
                color_text =  getResources().getColor(R.color.text_black);
                color_background = getResources().getColor(R.color.btn_background_normal);
                break;


            case 1:
                color_text = getResources().getColor(R.color.text_white);
                color_background = getResources().getColor(R.color.btn_background_sepia);
                break;

            case 2:
                color_text = getResources().getColor(R.color.text_white);
                color_background = getResources().getColor(R.color.btn_background_noche);
                break;
        }
        container.setBackgroundColor(color_background);
        for(int i=0;i<textViews.length;i++)
        {
            textViews[i].setTextColor(color_text);
        }
    }

    public void changeFontSize(int progress)
    {
        float fontiSze = 0f;
        for(int i=0;i<textViews.length;i++)
        {
            fontiSze = sizeTextView[i]+progress;
            textViews[i].setTextSize(fontiSze);
        }
    }

    public void changeFontFace(Typeface style)
    {
        for(int i=0;i<textViews.length;i++)
        {

            textViews[i].setTypeface(style);
            Log.w("valor",valores[0]);
        }
    }
}
