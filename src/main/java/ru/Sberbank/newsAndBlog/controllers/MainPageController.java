package ru.Sberbank.newsAndBlog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Sberbank.newsAndBlog.models.News;
import ru.Sberbank.newsAndBlog.repository.NewsRepository;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

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

    @GetMapping("/download")
    public String getFile() throws IOException {
        String path = "C://users//ab448//Downloads";
        File file = new File(path+"//news.txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        Iterable<News> news = newsRepository.findAll();
        Iterator<News> iterator = news.iterator();
        while (iterator.hasNext()) {
            News news_ = iterator.next();
            if (news_.getRedactor() == null) {
                fw.write(news_.getTime() + '\n');
                fw.write("anonymic" + '\n');
                fw.write(news_.getFullText() + '\n');
            } else {
                fw.write(news_.getTime() + '\n');
                fw.write(news_.getRedactor().getSurname() + '\n');
                fw.write(news_.getRedactor().getName() + '\n');
                fw.write(news_.getFullText() + '\n');

            }
        }
        fw.close();
        return "mainPage/success";
    }

}
