package com.example.smartfarm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ListerNote extends Fragment {

    private List<ClasseNote> NoteList;
    private RecyclerView.LayoutManager layoutManager;
    private NoteAdapter NoteAdapter;

    public ListerNote() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lister_note, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.note_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        NoteBDD db = new NoteBDD(getActivity());
        NoteList = db.getAllNote();
        NoteAdapter = new NoteAdapter(getActivity(), NoteList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(NoteAdapter);

        return view;
    }
}