package edu.pucmm.profile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import edu.pucmm.profile.databinding.ActivitySummaryBinding;

public class SummaryActivity extends AppCompatActivity{
    public final static String Nombre = "name";
    public final static String Id = "id";
    public final static String Carrera = "career";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivitySummaryBinding binding = ActivitySummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String nombre = getIntent().getStringExtra(Nombre);
        String id = getIntent().getStringExtra(Id);
        String carrera = getIntent().getStringExtra(Carrera);

        String primerNombre = nombre == null ? "" : nombre.trim().split(" ")[0];

        binding.welcomeText.setText(getString(R.string.summaryGreeting, primerNombre));
        binding.nametxt.setText(nombre);
        binding.idtxt.setText(id);
        binding.careertxt.setText(carrera);

        binding.editbtn.setOnClickListener(v -> finish());
    }
}
