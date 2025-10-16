package com.oddix.servelapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    private WebView webView;
    private TextView btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btnVolver = findViewById(R.id.btnVolver);
        webView = findViewById(R.id.webViewMap);

        // Botón volver
        btnVolver.setOnClickListener(v -> finish());

        // Configurar WebView
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);

        // Cargar HTML local
        webView.loadUrl("file:///android_asset/map.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
