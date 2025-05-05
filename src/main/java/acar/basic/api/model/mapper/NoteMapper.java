package acar.basic.api.model.mapper;

import acar.basic.api.model.dto.NoteDto;
import acar.basic.api.model.entity.Note;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE // İsteğe bağlı: Eşleşmeyen alanları ignore et
)
public interface NoteMapper {
    
    // Convert Note to NoteDto
    NoteDto toDto(Note note);

    // Convert NoteDto to Note
    Note toEntity(NoteDto noteDto);
    
    // Liste dönüşümü
    List<NoteDto> toDtoList(List<Note> notes);
}