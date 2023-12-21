package com.example.smartfarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.smartfarm.databinding.ActivityMainBinding;
import com.example.smartfarm.databinding.ActivityNoteBinding;


public class Note extends AppCompatActivity implements View.OnClickListener
{
    private ActivityNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAjoutFrag.setOnClickListener(this);
        binding.btnFragLister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if(view.equals(binding.btnAjoutFrag))
        {
            loadFragment(new AjouterNote());
        }

        if(view.equals(binding.btnFragLister))
        {
            loadFragment(new ListerNote());
        }


    }

    private void loadFragment(Fragment fragment)
    {
        Bundle bundle = new Bundle();
        NoteBDD db = new NoteBDD(Note.this);
        bundle.putSerializable("Clé", db);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment).commit();
    }
}
















































