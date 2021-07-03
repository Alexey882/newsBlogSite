package ru.Sberbank.newsAndBlog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Sberbank.newsAndBlog.models.News;
import ru.Sberbank.newsAndBlog.repository.NewsRepository;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MainPageController {
    NewsRepository newsRepository;
    @Autowired
    public MainPageController(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("title", "news");
        return "mainPage/mainPage";
    }

    @GetMapping("/downloads")
    public void getFile(HttpServletResponse response) {
        Path file = Paths.get("C://");
        if (Files.exists(file)){
            response.setHeader("Content-disposition", "attachment;filename=" + "news.txt");
            response.setContentType("application/vnd.ms-excel");

            try {
                Iterable<News> news = newsRepository.findAll();
               // Files.copy(file, );
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
    }

}
