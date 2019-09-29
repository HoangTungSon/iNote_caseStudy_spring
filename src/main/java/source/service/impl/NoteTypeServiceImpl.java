package source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import source.model.NoteType;
import source.repository.NoteTypeRepository;
import source.service.NoteTypeService;

public class NoteTypeServiceImpl implements NoteTypeService {

    @Autowired
    private NoteTypeRepository noteTypeRepository;

    @Override
    public Iterable<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }

    @Override
    public NoteType findById(Long id) {
        return noteTypeRepository.findOne(id);
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Long id) {
        noteTypeRepository.delete(id);
    }
}
