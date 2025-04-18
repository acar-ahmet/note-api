package acar.basic.api.service.implement;

import acar.basic.api.exception.NoteNotFoundException;
import acar.basic.api.model.dto.NoteDto;
import acar.basic.api.model.entity.Note;
import acar.basic.api.model.mapper.NoteMapper;
import acar.basic.api.repository.NoteRepository;
import acar.basic.api.service.interfaces.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImplement implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Note note = noteMapper.toEntity(noteDto);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toDto(savedNote);
    }

    @Override
    public NoteDto getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
        return noteMapper.toDto(note);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getNotesByTitle(String title) {
        return noteRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getNotesByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return noteRepository.findByDate(localDate).stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto updateNote(Long id, NoteDto noteDto) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));

        existingNote.setTitle(noteDto.getTitle());
        existingNote.setDate(noteDto.getDate());
        existingNote.setDescription(noteDto.getDescription());

        Note updatedNote = noteRepository.save(existingNote);
        return noteMapper.toDto(updatedNote);
    }

    @Override
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }
}
