package source.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import source.model.NoteType;
import source.service.NoteTypeService;

import java.util.Locale;

@Component
public class NoteTypeFormatter implements Formatter<NoteType> {
    private NoteTypeService noteTypeService;

    @Autowired
    public NoteTypeFormatter(NoteTypeService noteTypeService){
        this.noteTypeService = noteTypeService;
    }

    @Override
    public NoteType parse (String text, Locale locale){
        return noteTypeService.findById(Long.parseLong(text));
    }


    @Override
    public String print(NoteType object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
