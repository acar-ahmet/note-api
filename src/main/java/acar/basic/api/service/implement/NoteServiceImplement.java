package acar.basic.api.service.implement;

import acar.basic.api.exception.InvalidIdException;
import acar.basic.api.exception.NoteNotFoundException;
import acar.basic.api.model.dto.NoteDto;
import acar.basic.api.model.entity.Note;
import acar.basic.api.model.mapper.NoteMapper;
import acar.basic.api.repository.NoteRepository;
import acar.basic.api.repository.NoteRepositoryWithNativeQuery;
import acar.basic.api.repository.NoteRepositoryWithQuery;
import acar.basic.api.service.interfaces.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImplement implements NoteService {
    ///Change repository class for usage
    private final NoteRepositoryWithNativeQuery noteRepository;
    ///private final NoteRepositoryWithQuery noteRepository;
    ///private final NoteRepository noteRepository;
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
    public List<NoteDto> getNotesByDescription(String description) {
        return noteRepository.findByDescriptionContainingIgnoreCase(description).stream()
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
    public List<NoteDto> getNotesByTitleAndDate(String title,String dateText) {
        LocalDate date= LocalDate.parse(dateText);
        System.out.println(date);
        return noteRepository.findByTitleAndDate(title,date).stream()
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
    public List<NoteDto> getNotesByDateBetween(String startdate,String enddate) {
        LocalDate startDate = LocalDate.parse(startdate);
        LocalDate endDate = LocalDate.parse(enddate);
        return noteRepository.findByDateBetween(startDate,endDate).stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto updateNote(Long id, NoteDto noteDto) {
        if (!id.equals(noteDto.getId())) {
            throw new InvalidIdException("For update, bad request: Path ID and body ID mismatch");
        }
        //check if the item exist
        noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
        noteDto.setId(id);
        Note updatedNote = noteRepository.save(noteMapper.toEntity(noteDto));
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
