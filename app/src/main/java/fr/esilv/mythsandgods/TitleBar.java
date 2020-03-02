package fr.esilv.mythsandgods;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class TitleBar extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean customTitleSupported =
                requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.content_title_bar);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.myTitle);
        if (myTitleText != null) {
            myTitleText.setText("@string");
            // user can also set color using "Color" and then
            // "Color value constant"
            // myTitleText.setBackgroundColor(Color.GREEN);
        }
    }
}