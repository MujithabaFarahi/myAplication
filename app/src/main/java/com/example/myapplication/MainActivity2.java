package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity2 extends AppCompatActivity {

    private Button targetOkButton;
    private Button multiPlayerButton;
    private EditText score;
    private SwitchCompat switchHard;

    private boolean switchBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        score = findViewById(R.id.score);
        targetOkButton = findViewById(R.id.targetOkButton);
        multiPlayerButton = findViewById(R.id.multiPlayerButton);
        switchHard = findViewById(R.id.switchHard);

        targetOkButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();

            if (score.getText() == null || score.getText().toString().isBlank()) {
                bundle.putInt("target", 101);
            } else {
                bundle.putInt("target", Integer.parseInt(score.getText().toString()));
            }
            bundle.putBoolean("hardMode", switchBoolean);

            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtras(bundle);

            startActivity(intent);
        });

        multiPlayerButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();

            if (score.getText() == null || score.getText().toString().isBlank()) {
                bundle.putInt("target", 101);
            } else {
                bundle.putInt("target", Integer.parseInt(score.getText().toString()));
            }

            Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
            intent.putExtras(bundle);

            startActivity(intent);
        });

        switchHard.setOnClickListener(v -> switchBoolean = switchHard.isChecked());
    }
}
