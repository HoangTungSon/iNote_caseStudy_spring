package source.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import source.model.NoteType;

public interface NoteTypeRepository extends PagingAndSortingRepository<NoteType, Long> {
}
