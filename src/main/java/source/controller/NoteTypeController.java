package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import source.model.Note;
import source.model.NoteType;
import source.service.NoteService;
import source.service.NoteTypeService;

@Controller
public class NoteTypeController {
    @Autowired
    private NoteTypeService noteTypeService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/types")
    public ModelAndView typesList(){
        Iterable<NoteType> noteTypes = noteTypeService.findAll();
        return new ModelAndView("/noteType/list", "noteTypes", noteTypes);
    }

    @GetMapping("/create-noteType")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("noteType", new NoteType());
        return modelAndView;
    }

    @PostMapping("/create-noteType")
    public ModelAndView saveNoteType(@ModelAttribute("noteType") NoteType noteType){
        noteTypeService.save(noteType);

        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("noteType", new NoteType());
        modelAndView.addObject("message", "New noteType created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-noteType/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        NoteType noteType = noteTypeService.findById(id);
        if(noteType != null) {
            ModelAndView modelAndView = new ModelAndView("/noteType/edit");
            modelAndView.addObject("noteType", noteType);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-noteType")
    public ModelAndView updateNoteType(@ModelAttribute("noteType") NoteType noteType){
        noteTypeService.save(noteType);
        ModelAndView modelAndView = new ModelAndView("/noteType/edit");
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("message", "NoteType updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-noteType/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        NoteType noteType = noteTypeService.findById(id);
        if(noteType != null) {
            ModelAndView modelAndView = new ModelAndView("/noteType/delete");
            modelAndView.addObject("noteType", noteType);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-noteType")
    public String removeNoteType(@ModelAttribute("noteType") NoteType noteType){
        noteTypeService.remove(noteType.getId());
        return "redirect:types";
    }

    @GetMapping("/view-noteType/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        NoteType noteType = noteTypeService.findById(id);
        if(noteType == null){
            return new ModelAndView("/error.404");
        }
        Iterable<Note> notes = noteService.findAllByNoteType(noteType);
        ModelAndView modelAndView = new ModelAndView("/noteType/view");
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }
}
