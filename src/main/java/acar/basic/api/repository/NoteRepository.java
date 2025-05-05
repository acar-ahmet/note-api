package acar.basic.api.repository;

import acar.basic.api.model.entity.Note;
import org.springframework.lang.NonNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // CRUD işlemleri JpaRepository tarafından sağlanır
    
    // Find notes by title (case-insensitive)
    List<Note> findByTitleContainingIgnoreCase(String title);
    
    // Find notes by date
    List<Note> findByDate(LocalDate date);
    
    // Find notes between two dates
    List<Note> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Find notes by description containing text (case-insensitive)
    List<Note> findByDescriptionContainingIgnoreCase(String description);
    
    // Find notes by title and date
    List<Note> findByTitleAndDate(String title, LocalDate date);
    
    
    // Delete note by ID
    @Override
    void deleteById(@NonNull Long id);
}
