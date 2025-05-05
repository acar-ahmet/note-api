package acar.basic.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import acar.basic.api.model.dto.NoteDto;
import acar.basic.api.service.interfaces.NoteService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Not oluşturma (POST)
    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody @Valid NoteDto noteDto) {
        NoteDto createdNote = noteService.createNote(noteDto); // Notu oluştur
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED); // 201 Created döner
    }

    // ID'ye göre not getirme (GET)
    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
        NoteDto note = noteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    // Tüm notları getirme (GET)
    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<NoteDto> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }
    // Açıklamaya göre not getirme (GET)
    @GetMapping("/description/{description}")
    public ResponseEntity<List<NoteDto>> getNotesByDescription(@PathVariable String description) {
        List<NoteDto> notes = noteService.getNotesByDescription(description);
        return ResponseEntity.ok(notes);
    }
    // Başlığa göre not getirme (GET)
    @GetMapping("/title/{title}")
    public ResponseEntity<List<NoteDto>> getNotesByTitle(@PathVariable String title) {
        List<NoteDto> notes = noteService.getNotesByTitle(title);
        return ResponseEntity.ok(notes);
    }
    @GetMapping("/title/{title}/date/{date}")
    public ResponseEntity<List<NoteDto>> getNotesByTitleAndDate(@PathVariable String title,@PathVariable String date) {
        System.out.println(title+date);
        List<NoteDto> notes = noteService.getNotesByTitleAndDate(title,date);
        return ResponseEntity.ok(notes);
    }
    // Tarihe göre not getirme (GET)
    @GetMapping("/date/{date}")
    public ResponseEntity<List<NoteDto>> getNotesByDate(@PathVariable String date) {
        List<NoteDto> notes = noteService.getNotesByDate(date);
        return ResponseEntity.ok(notes);
    }
    @GetMapping("/date/{startdate}/{enddate}")
    public ResponseEntity<List<NoteDto>> getNotesByBetweenDate(@PathVariable String startdate,@PathVariable String enddate) {
        List<NoteDto> notes = noteService.getNotesByDateBetween(startdate,enddate);
        return ResponseEntity.ok(notes);
    }

    // Not güncelleme (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id, @RequestBody @Valid NoteDto noteDto) {
        NoteDto updatedNote = noteService.updateNote(id, noteDto);
        return ResponseEntity.ok(updatedNote);
    }

    // Not silme (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id); // Notu sil
        return ResponseEntity.noContent().build(); // 204 No Content döner
    }
}
