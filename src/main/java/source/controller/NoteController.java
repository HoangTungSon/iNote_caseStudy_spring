package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import source.model.Note;
import source.model.NoteType;
import source.service.NoteService;
import source.service.NoteTypeService;

import java.util.Optional;


@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Iterable<NoteType> noteTypes(){
        return noteTypeService.findAll();
    }

    @GetMapping("/notes")
    public ModelAndView noteList(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Note> notes;
        if(s.isPresent()) {
              notes  = noteService.findAllByTitleContaining(s.get(), pageable);
        } else {
            notes = noteService.findAll(pageable);
        }
        return new ModelAndView("/iNote/list", "notes", notes);
    }

    @GetMapping("/view-note/{id}")
    public ModelAndView viewNote(@PathVariable("id") Long id){
        Note note = noteService.findById(id);
        return new ModelAndView("/iNote/view", "note", note);
    }

    @GetMapping("/create-note")
    public ModelAndView createNoteForm() {
        return new ModelAndView("/iNote/create", "note", new Note());
    }

    @PostMapping("/create-note")
    public ModelAndView saveNote(@ModelAttribute("note") Note note){
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/iNote/create");
        modelAndView.addObject("message", "successfully create a new iNote");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Note note = noteService.findById(id);
        if(note != null) {
            ModelAndView modelAndView = new ModelAndView("/iNote/edit");
            modelAndView.addObject("note", note);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-note")
    public ModelAndView updateNote(@ModelAttribute("note") Note note){
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/iNote/edit");
        modelAndView.addObject("note", note);
        modelAndView.addObject("message", "Note updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Note note = noteService.findById(id);
        if(note != null) {
            ModelAndView modelAndView = new ModelAndView("/iNote/delete");
            modelAndView.addObject("note", note);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute("note") Note note){
        noteService.remove(note.getId());
        return "redirect:notes";
    }
}
