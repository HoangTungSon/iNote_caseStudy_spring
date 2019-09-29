package source.service;


import source.model.NoteType;

public interface NoteTypeService {
    Iterable<NoteType> findAll();

    NoteType findById(Long id);

    void save (NoteType noteType);

    void remove (Long id);
}
