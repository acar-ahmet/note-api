package acar.basic.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import acar.basic.api.model.entity.Note;

@Repository
public interface NoteRepositoryWithNativeQuery extends NoteRepository {
    
    @Query(value = "SELECT * FROM note WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%'))", 
          nativeQuery = true)
    List<Note> findByTitleContainingIgnoreCase(@Param("title") String title);
    
    @Query(value = "SELECT * FROM note WHERE date = :date", 
          nativeQuery = true)
    List<Note> findByDate(@Param("date") LocalDate date);
    
    @Query(value = """
        SELECT * FROM note
        WHERE date BETWEEN :startDate AND :endDate 
        ORDER BY title ASC
        """, nativeQuery = true)
    List<Note> findByDateBetween(@Param("startDate") LocalDate startDate, 
                               @Param("endDate") LocalDate endDate);
    
    @Query(value = "SELECT * FROM note WHERE LOWER(description) LIKE LOWER(CONCAT('%', :description, '%'))", 
          nativeQuery = true)
    List<Note> findByDescriptionContainingIgnoreCase(@Param("description") String description);
    
    @Query(value = """
        SELECT * FROM note 
        WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%')) 
        AND date = :date
        """, nativeQuery = true)
    List<Note> findByTitleAndDate(@Param("title") String title, 
                                @Param("date") LocalDate date);
    
    @Modifying
    @Override
    @Query(value = "DELETE FROM note WHERE id = :id", 
          nativeQuery = true)
    void deleteById(@Param("id") @NonNull Long id);

    @Query(value = "SELECT * FROM note ORDER BY date DESC", 
          nativeQuery = true)
    @NonNull 
    List<Note> findAll();
}
