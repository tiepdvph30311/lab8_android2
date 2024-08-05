package tiepdvph30311.fpoly.lab8_android2.Fragment;

//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class NotesListFragment extends Fragment {
//    private RecyclerView recyclerView;
//    private NotesAdapter adapter;
//    private NoteViewModel noteViewModel;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
//
//        recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter = new NotesAdapter(new ArrayList<>());
//        recyclerView.setAdapter(adapter);
//
//        // Initialize ViewModel
//        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
//
//        // Observe the LiveData from ViewModel
//        noteViewModel.getNotes().observe(getViewLifecycleOwner(), notes -> {
//            adapter.setNotes(notes);
//        });
//
//        // Set OnItemClickListener
//        adapter.setOnItemClickListener(note -> {
//            // Handle item click
//            showNoteDetailDialog(note);
//        });
//
//        // Set OnItemLongClickListener
//        adapter.setOnItemLongClickListener(note -> {
//            showDeleteConfirmationDialog(note);
//        });
//
//        return view;
//    }
//
//    private void showNoteDetailDialog(Note note) {
//        // Inflate the dialog layout
//        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_note_detail, null);
//
//        // Find and set the TextViews
//        TextView contentTextView = dialogView.findViewById(R.id.dialog_content);
//        TextView timeTextView = dialogView.findViewById(R.id.dialog_time);
//
//        contentTextView.setText(note.getContent());
//        timeTextView.setText(note.getTime());
//
//        // Create and show the dialog
//        new AlertDialog.Builder(getContext())
//                .setView(dialogView)
//                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
//                .create()
//                .show();
//    }
//
//    private void showDeleteConfirmationDialog(Note note) {
//        new AlertDialog.Builder(getContext())
//                .setTitle("Xóa ghi chú")
//                .setMessage("Bạn có chắc chắn muốn xóa ghi chú này?")
//                .setPositiveButton("Xóa", (dialog, which) -> {
//                    noteViewModel.deleteNote(note.getId());
//                    Toast.makeText(getContext(), "Ghi chú đã được xóa", Toast.LENGTH_SHORT).show();
//                })
//                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
//                .create()
//                .show();
//    }
//}
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiepdvph30311.fpoly.lab8_android2.Model.Note;
import tiepdvph30311.fpoly.lab8_android2.Model.NoteViewModel;
import tiepdvph30311.fpoly.lab8_android2.Adapter.NotesAdapter;
import tiepdvph30311.fpoly.lab8_android2.R;

public class NotesListFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private NoteViewModel noteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NotesAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Initialize ViewModel
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Observe the LiveData from ViewModel
        noteViewModel.getNotes().observe(getViewLifecycleOwner(), notes -> {
            adapter.setNotes(notes);
        });

        // Set OnItemClickListener
        adapter.setOnItemClickListener(note -> {
            // Handle item click
            showNoteDetailDialog(note);
        });

        // Set OnDeleteClickListener
        adapter.setOnDeleteClickListener(note -> {
            showDeleteConfirmationDialog(note);
        });

        return view;
    }

    private void showNoteDetailDialog(Note note) {
        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_note_detail, null);

        // Find and set the TextViews
        TextView contentTextView = dialogView.findViewById(R.id.dialog_content);
        TextView timeTextView = dialogView.findViewById(R.id.dialog_time);

        contentTextView.setText(note.getContent());
        timeTextView.setText(note.getTime());

        // Create and show the dialog
        new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void showDeleteConfirmationDialog(Note note) {
        new AlertDialog.Builder(getContext())
                .setTitle("Xóa ghi chú")
                .setMessage("Bạn có chắc chắn muốn xóa ghi chú này?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    noteViewModel.deleteNote(note.getId());
                    Toast.makeText(getContext(), "Ghi chú đã được xóa", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
