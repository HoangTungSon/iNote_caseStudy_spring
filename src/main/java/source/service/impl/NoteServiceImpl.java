package source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import source.model.Note;
import source.model.NoteType;
import source.repository.NoteRepository;
import source.service.NoteService;

public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void remove(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public Iterable<Note> findAllByNoteType(NoteType noteType) {
        return noteRepository.findAllByNoteType(noteType);
    }

    @Override
    public Page<Note> findAllByTitleContaining(String title, Pageable pageable) {
        return noteRepository.findAllByTitleContaining(title, pageable);
    }
}
