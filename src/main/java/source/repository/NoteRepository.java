package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import source.model.Note;
import source.model.NoteType;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Iterable<Note> findAllByNoteType(NoteType noteType);

    Page<Note> findAllByTitleContaining(String title, Pageable pageable);
}
