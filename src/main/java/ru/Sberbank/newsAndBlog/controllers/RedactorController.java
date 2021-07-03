package ru.Sberbank.newsAndBlog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.Sberbank.newsAndBlog.models.Redactor;
import ru.Sberbank.newsAndBlog.repository.RedactorRepository;
@Controller
@RequestMapping("/redactor")
public class RedactorController {
    RedactorRepository redactorRepository;
    @Autowired
    public RedactorController(RedactorRepository redactorRepository) {
        this.redactorRepository = redactorRepository;
    }
@GetMapping()
    public String show(Model model){
    model.addAttribute("title" , "redactors");
    Iterable<Redactor> redactors = redactorRepository.findAll();
    model.addAttribute("space" , "_____");
    model.addAttribute("redactors" , redactors);
    return "redactorPages/showRedactors";
}

@GetMapping("/create")
    public String createProfile(Model model){
        model.addAttribute("redactor" , new Redactor());
        model.addAttribute("title", "create");
        return "redactorPages/createProfile";
}
@GetMapping("/successPage")
public String success(){
        return "redactorPages/successPage";
}
@PostMapping()
    public String createAndSave(@ModelAttribute("redactor")Redactor redactor , Model model){
     redactorRepository.save(redactor);
     model.addAttribute("title","success");
     return "redirect:/successPage";
}



}
