package tiepdvph30311.fpoly.lab8_android2.Model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import tiepdvph30311.fpoly.lab8_android2.DAO.NotesDAO;

public class NoteViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Note>> notesLiveData;
    private final NotesDAO notesDAO;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        notesDAO = new NotesDAO(application.getApplicationContext());
        notesDAO.open();  // Mở cơ sở dữ liệu
        notesLiveData = new MutableLiveData<>();
        loadNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notesLiveData;
    }

    public void addNote(String content, String time) {
        notesDAO.createNote(content, time);
        loadNotes();  // Reload notes to update LiveData
    }

    public void deleteNote(long noteId) {
        notesDAO.deleteNote(noteId);
        loadNotes();  // Reload notes to update LiveData
    }

    private void loadNotes() {
        List<Note> notes = notesDAO.getAllNotes();
        notesLiveData.setValue(notes);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        notesDAO.close();  // Đóng cơ sở dữ liệu khi ViewModel bị hủy
    }
}
