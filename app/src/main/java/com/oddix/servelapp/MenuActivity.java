package com.oddix.servelapp;
import android.content.Intent;
import java.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnFoto = findViewById(R.id.btnFoto);
        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnWeb = findViewById(R.id.btnServel);
        Button btnCalendario = findViewById(R.id.btnCalendario);

        // Evento explícito
        btnFoto.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, PhotoActivity.class);
            startActivity(intent);
        });


        btnMapa.setOnLongClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MapActivity.class);
            startActivity(intent);
            return true;
        });


        // Evento implícito
        btnMapa.setOnClickListener(v -> {
            Uri geoUri = Uri.parse("geo:-33.45,-70.66?q=Plaza+de+Armas+Santiago");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
            startActivity(mapIntent);

        });

        // Evento implícito
        btnWeb.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://consulta.servel.cl/"));
            startActivity(intent);
        });

        // Evento implícito
        btnCalendario.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE, "Votaciones presidenciales - Primera vuelta");
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "https://consulta.servel.cl/");

            Calendar inicio = Calendar.getInstance();
            inicio.set(2025, Calendar.NOVEMBER, 16, 9, 0);
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, inicio.getTimeInMillis());
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
            startActivity(intent);
        });
    }
}
