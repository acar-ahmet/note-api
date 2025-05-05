package acar.basic.api.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Long id;

    @NotBlank(message = "Başlık boş olamaz")
    private String title;

    @NotNull(message = "Tarih boş olamaz")
    private LocalDate date;

    private String description;
}
