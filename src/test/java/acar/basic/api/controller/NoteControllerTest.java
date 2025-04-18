package acar.basic.api.controller;

import acar.basic.api.model.dto.NoteDto;
import acar.basic.api.service.interfaces.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private NoteService noteService;

    @Test
    void createNote_ValidInput_ReturnsCreated() throws Exception {
        // Given
        NoteDto inputDto = NoteDto.builder()
                .title("Test Note")
                .date(LocalDate.now())
                .description("Test Description")
                .build();

        NoteDto savedDto = NoteDto.builder()
                .id(1L)
                .title("Test Note")
                .date(LocalDate.now())
                .description("Test Description")
                .build();

        when(noteService.createNote(any(NoteDto.class))).thenReturn(savedDto);

        // When & Then
        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Note"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void createNote_EmptyTitle_ReturnsBadRequest() throws Exception {
        // Given
        NoteDto inputDto = NoteDto.builder()
                .title("")
                .date(LocalDate.now())
                .description("Test Description")
                .build();

        // When & Then
        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createNote_NullDate_ReturnsBadRequest() throws Exception {
        // Given
        NoteDto inputDto = NoteDto.builder()
                .title("Test Note")
                .date(null)
                .description("Test Description")
                .build();

        // When & Then
        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createNote_InvalidDateFormat_ReturnsBadRequest() throws Exception {
        // Given
        String invalidJson = """
            {
                "title": "Test Note",
                "date": "25-03-2024T00:00:00",
                "description": "Test Description"
            }
            """;

        // When & Then
        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
} 