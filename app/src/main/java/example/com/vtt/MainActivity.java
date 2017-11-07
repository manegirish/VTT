package example.com.vtt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQ_CODE_SPEECH_INPUT = 100;

    private TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = findViewById(R.id.activity_toolbar_layout);
        setSupportActionBar(toolbar);

        mainText = findViewById(R.id.main_activity_text);

        FloatingActionButton recordAudio = findViewById(R.id.record_audio_fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            recordAudio.setTooltipText("Click here to start recorder");
        }
        recordAudio.setOnClickListener(this);

    }

    // Showing google speech input dialog
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private String output(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Word: ");
        stringBuilder.append(word);
        stringBuilder.append("\n");
        stringBuilder.append("Code word:");
        stringBuilder.append(MakeCode_.getCode(word));
        stringBuilder.append("\n");
        stringBuilder.append("Command code: ");
        int code = Dictionary_.getCode(MakeCode_.getCode(word));
        stringBuilder.append(code);
        stringBuilder.append("\n");
        if (code <= 0) {
            stringBuilder.append("Invalid Voice Command");
        } else {
            stringBuilder.append("Valid Voice Command");
        }
        return stringBuilder.toString();
    }

    // Receiving speech input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.e(MainActivity.class.getSimpleName(), "result: " + result);
                    mainText.setText(output(result.get(0)));
                }
                break;
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_audio_fab:
                promptSpeechInput();
                break;
        }
    }
}
