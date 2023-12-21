package com.example.smartfarm;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfarm.databinding.RecyclerviewitemBinding;
import com.example.smartfarm.databinding.RecyclerviewitemBinding;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<ClasseNote> listeNote;
    private Context context;

    public NoteAdapter(Context context, List<ClasseNote> listeNote)
    {
        this.context = context;
        this.listeNote = listeNote;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerviewitemBinding binding = RecyclerviewitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, @SuppressLint("RecyclerView") final int position)
    {
       ClasseNote note = listeNote.get(position);
        holder.bind(note);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation de Suppression");
                builder.setMessage("Suppression de cette note " + listeNote.get(position).getContent() + " de cette liste");

                builder.setPositiveButton("Oui !", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        NoteBDD db = new NoteBDD(context);
                        db.deleteNote(listeNote.get(position));
                        Toast.makeText(context, "Suppression note " + position + " " + listeNote.get(position).getContent() + " avec succès", Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                        int positionStart = holder.getAdapterPosition();
                        listeNote.remove(positionStart);
                        notifyItemRemoved(positionStart);
                    }
                });

                builder.setNegativeButton("Non !", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.create().show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listeNote.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder
    {
        private final RecyclerviewitemBinding binding;

        public NoteViewHolder(RecyclerviewitemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ClasseNote note)
        {

            binding.txtvnote.setText( note.getContent());

        }
    }
}
