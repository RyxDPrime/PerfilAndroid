package edu.pucmm.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.pucmm.profile.databinding.ActivityProfileBinding;

public class ProfileView extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] arrays = {"Seleccione la carrera", "Telematica", "Computacion", "Derecho"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.cbmCareer.setAdapter(adapter);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationAndSave();
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    @SuppressLint("SetTextI18n")
    private void validationAndSave() {
        AtomicBoolean validated = new AtomicBoolean(true);

        String name = binding.txtname.getText().toString().trim();
        String id = binding.txtId.getText().toString().trim();

        if(name.isEmpty()){
            binding.txtname.setError("El nombre es obligatorio");
            validated.set(false);
        }

        if(id.isEmpty()){
            binding.txtId.setError("La matricula es obligatoria");
            validated.set(false);
        }

        if (binding.cbmCareer.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) binding.cbmCareer.getSelectedView();
            if (errorText != null) {
                errorText.setTextColor(android.graphics.Color.RED);
                errorText.setText("Debes seleccionar una carrera");
            }
            validated.set(false);
        }

        if(validated.get()){
            Toast.makeText(this, "Perfil Guardado Correctamente", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtra(SummaryActivity.Nombre, name);
            intent.putExtra(SummaryActivity.Id, id);
            intent.putExtra(SummaryActivity.Carrera, (String)binding.cbmCareer.getSelectedItem());

            startActivity(intent);
        }
    }
}