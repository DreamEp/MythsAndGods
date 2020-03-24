package fr.esilv.mythsandgods.ViewPager;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.esilv.mythsandgods.R;

public class SummaryCategoryFragment extends Fragment {
    private TextView mTextView;
    private String summary;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_summary_category, container, false);
        summary = getArguments().getString("key_summary");

        mTextView = (TextView) v.findViewById(R.id.textView_summary);
        mTextView.setText(summary);
        mTextView.setMovementMethod(new ScrollingMovementMethod());


        return v;
    }
}
