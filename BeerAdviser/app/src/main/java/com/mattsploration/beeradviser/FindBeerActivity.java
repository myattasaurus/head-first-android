package com.mattsploration.beeradviser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends Activity {

    private BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);

        Button findBeerButton = findViewById(R.id.find_beer);
        findBeerButton.setOnClickListener((view) -> {
            Spinner colors = findViewById(R.id.color);
            TextView brandsTextView = findViewById(R.id.brands);

            String color = colors.getSelectedItem().toString();
            List<String> brands = beerExpert.getBrands(color);

            StringBuilder sb = new StringBuilder();
            brands.forEach((brand) -> sb.append(brand).append('\n'));

            brandsTextView.setText(sb.toString());
        });
    }
}