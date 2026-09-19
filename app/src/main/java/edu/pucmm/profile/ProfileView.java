package edu.pucmm.profile;

import android.annotation.SuppressLint;
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

        String[] arrays = {null, "Telematica", "Computacion", "Derecho"};
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            if (binding.txtname.getText().isEmpty()) {
                binding.txtname.setError("El campo no puede estar vacio");
                validated.set(false);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            if (binding.txtId.getText().isEmpty()) {
                binding.txtId.setError("El campo no puede estar vacio");
                validated.set(false);
            }
        }
        if(binding.cbmCareer.getSelectedItem() == null){
            TextView errorText = (TextView) binding.cbmCareer.getSelectedView();
            if (errorText != null) {
                errorText.setTextColor(android.graphics.Color.RED);
                errorText.setText("Debes seleccionar una carrera");
                validated.set(false);
            }
        }

        if(validated.get()){
            Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
        }
    }
}