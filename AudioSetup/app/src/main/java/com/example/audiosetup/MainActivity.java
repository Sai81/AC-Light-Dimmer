package com.example.audiosetup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String  TAG="MainActivity";

    public TextView textView;
    public TextView speechtext;
    public SeekBar SeekBar;
    public ImageButton imageButton;

    private static final int Recognizer=1;
    private long lastChangeTime=0;
    public static final long DELTA_MS=200;
    private EditText ipAddress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode==Recognizer && resultCode==RESULT_OK){
            ArrayList<String> matches= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            speechtext.setText(matches.get(0).toString());

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        speechtext=findViewById(R.id.textView2);
        SeekBar=findViewById(R.id.seekBar);
        imageButton=findViewById(R.id.imageButton);
        ipAddress=findViewById(R.id.ipAddress);

        ipAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipAddress.getText().toString();
                Log.d(TAG,"ipaddress "+ip);
                Repository.setIP(ip);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech TO Text");
                startActivityForResult(speechIntent,Recognizer);
            }
        });






        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(""+progress+"%");
                Log.d(TAG,"Main  speed:"+progress);
                long now= System.currentTimeMillis();
                //if ( (now -  lastChangeTime) >= DELTA_MS ) {
                    Log.d(TAG,"Really setting speed:"+progress);
                    Repository.get().setSpeed(progress);
                    lastChangeTime = now;
                //+ }
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }
}
