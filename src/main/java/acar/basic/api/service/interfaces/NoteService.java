package acar.basic.api.service.interfaces;

import acar.basic.api.model.dto.NoteDto;
import java.util.List;

public interface NoteService {
    // Create
    NoteDto createNote(NoteDto noteDto);
    
    // Read
    NoteDto getNoteById(Long id);
    List<NoteDto> getAllNotes();
    List<NoteDto> getNotesByDescription(String description);
    List<NoteDto> getNotesByTitle(String title);
    List<NoteDto> getNotesByTitleAndDate(String title,String dateText);
    List<NoteDto> getNotesByDate(String date);
    List<NoteDto> getNotesByDateBetween(String startdate,String enddate);
    
    // Update
    NoteDto updateNote(Long id, NoteDto noteDto);
    
    // Delete
    void deleteNote(Long id);
}
