package source.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import source.model.Note;
import source.model.NoteType;

public interface NoteService {
    Page<Note> findAll(Pageable pageable);

    Note findById(Long id);

    void save (Note note);

    void remove (Long id);

    Iterable<Note> findAllByNoteType(NoteType noteType);

    Page<Note> findAllByTitleContaining(String title, Pageable pageable);
}
