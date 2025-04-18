package acar.basic.api.model.mapper;

import acar.basic.api.model.dto.NoteDto;
import acar.basic.api.model.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    // Convert Note to NoteDto
    public NoteDto toDto(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .date(note.getDate())
                .description(note.getDescription())
                .build();
    }

    // Convert NoteDto to Note
    public Note toEntity(NoteDto noteDto) {
        return Note.builder()
                .id(noteDto.getId())
                .title(noteDto.getTitle())
                .date(noteDto.getDate())
                .description(noteDto.getDescription())
                .build();
    }
}
