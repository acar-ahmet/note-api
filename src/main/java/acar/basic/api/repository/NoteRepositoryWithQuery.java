package acar.basic.api.repository;

import acar.basic.api.model.entity.Note;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NoteRepositoryWithQuery extends NoteRepository {
    
    @Query("SELECT n FROM Note n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Note> findByTitleContainingIgnoreCase(@Param("title") String title);
    
    @Query("SELECT n FROM Note n WHERE n.date = :date")
    List<Note> findByDate(@Param("date") LocalDate date);
    
    @Query("SELECT n FROM Note n WHERE n.date BETWEEN :startDate AND :endDate ORDER BY n.title ASC")
    List<Note> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT n FROM Note n WHERE LOWER(n.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Note> findByDescriptionContainingIgnoreCase(@Param("description") String description);
    
    @Query("SELECT n FROM Note n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%')) AND n.date = :date")
    List<Note> findByTitleAndDate(@Param("title") String title, 
                                @Param("date") LocalDate date);
    
    @Modifying
    @Override
    @Query("DELETE FROM Note n WHERE n.id = :id")
    void deleteById(@Param("id") @NonNull Long id);

    @Query("SELECT n FROM Note n ORDER BY n.date DESC")
    @NonNull List<Note> findAll();
}
