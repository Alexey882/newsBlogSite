package ru.Sberbank.newsAndBlog.controllers;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Sberbank.newsAndBlog.models.News;
import ru.Sberbank.newsAndBlog.models.Redactor;
import ru.Sberbank.newsAndBlog.repository.NewsRepository;
import ru.Sberbank.newsAndBlog.repository.RedactorRepository;
import java.util.*;
@Controller
@RequestMapping("/news")
public class NewsController {
    NewsRepository newsRepository;
    RedactorRepository redactorRepository;
    @Autowired
    public NewsController(NewsRepository newsRepository, RedactorRepository redactorRepository) {
        this.newsRepository = newsRepository;
        this.redactorRepository = redactorRepository;
    }
    @GetMapping
    public String showBest(Model model) {
        Iterable<News> news = newsRepository.findBestNews();
        Iterator<News> iterator = news.iterator();
        ArrayList<ArrayList<String>>news_info = new ArrayList<>();
        while(iterator.hasNext()){
            News news_ = iterator.next();
            ArrayList<String> news_1 = new ArrayList<>();
            if(news_.getRedactor()==null){
                news_1.add(news_.getTime());
                news_1.add("anonymic");
                news_1.add(news_.getFullText());
            }
            else{
                news_1.add(news_.getTime());
                news_1.add(news_.getRedactor().getSurname());
                news_1.add(news_.getRedactor().getName());
                news_1.add(news_.getFullText());
            }
            news_info.add(news_1);
        }
        model.addAttribute("news", news_info);
        model.addAttribute("title", "breaking news");
        model.addAttribute("space", "______");
      //  Iterable<Redactor> redactors = redactorRepository.findAll();
        return "newsPages/showBestNews";
    }
    @GetMapping("/addAnonymousNews")
    public String addAnonNews(Model model) {
        model.addAttribute("title", "add");
        model.addAttribute("news" , new News());
        return "newsPages/addAnonymousNews";
    }
    @PostMapping
    public String createAnonymousNews(@ModelAttribute("news") News news) {
        news.setTime(new Date().toString());
        newsRepository.save(news);
        return "mainPage/mainPage";
    }

    @GetMapping("/addNews")
    public String addNews(Model model) {
        model.addAttribute("title", "add news");
        model.addAttribute("news" , new News());
        model.addAttribute("redactor" , new Redactor()) ;
        return "newsPages/addNews";
    }

    @PostMapping("/create")
    public String createNews(@ModelAttribute("news") News news , @ModelAttribute("redactor")Redactor redactor) {
        news.setTime(new Date().toString());
        news.setRedactor(redactor);
        newsRepository.save(news);
        return "mainPage/mainPage";
    }
    @GetMapping("/read")
    public String readNews(Model model) {
        Iterable<News> news = newsRepository.findAll();
        Iterator<News> iterator = news.iterator();
        ArrayList<ArrayList<String>>news_info = new ArrayList<>();
        while(iterator.hasNext()){
            News news_ = iterator.next();
            ArrayList<String> news_1 = new ArrayList<>();
            if(news_.getRedactor()==null){
                news_1.add(news_.getTime());
                news_1.add("anonymic");
                news_1.add(news_.getFullText());
            }
            else{
                news_1.add(news_.getTime());
                news_1.add(news_.getRedactor().getSurname());
                news_1.add(news_.getRedactor().getName());
                news_1.add(news_.getFullText());
            }
            news_info.add(news_1);
        }
        model.addAttribute("news" , news_info);
        model.addAttribute("space", "______");
        model.addAttribute("title", "all news");
        return "newsPages/showAllNews";
    }
    @GetMapping("/{id}")
    public String readByID(@PathVariable("id") long id, Model model) {
        News news_ = newsRepository.findByID(id);
        ArrayList<String> news = new ArrayList<>();
        if(news_.getRedactor()==null){
            news.add(news_.getTime());
            news.add("anonymic");
            news.add(news_.getFullText());
        }
        else{
            news.add(news_.getTime());
            news.add(news_.getRedactor().getSurname());
            news.add(news_.getRedactor().getName());
            news.add(news_.getFullText());
        }
        model.addAttribute("news",news);
        model.addAttribute("title", "some news");
        return "newsPages/readById";
    }

    @GetMapping("/category/{category}")
    public String showCategoryNews(@PathVariable("category")String category,Model model)
    {
        model.addAttribute("title" , "category");
        Iterable<News> news__ = newsRepository.findAllByCategory(category);
        Iterator<News> iterator = news__.iterator();
        ArrayList<ArrayList<String>> news_1 = new ArrayList<>();
        while(iterator.hasNext()) {
            News news_ = iterator.next();
            ArrayList<String> news = new ArrayList<>();
            if (news_.getRedactor() == null) {
                news.add(news_.getTime());
                news.add("anonymic");
                news.add(news_.getFullText());
            } else {
                news.add(news_.getTime());
                news.add(news_.getRedactor().getSurname());
                news.add(news_.getRedactor().getName());
                news.add(news_.getFullText());
            }
            news_1.add(news);
        }
        model.addAttribute("newsByCategory" , news_1);
        model.addAttribute("space" , "_____");
     return "newsPages/someCategory";
    }
    @GetMapping("/category")
    public String showCategories(Model model){
        model.addAttribute("category" , newsRepository.findAllCategory());
        model.addAttribute("title" , "category");
        return "newsPages/showCategory";
    }
    @GetMapping("/showByMail/{mail}")
    public String showByMail(@PathVariable("mail")String mail ,  Model model){
        model.addAttribute("title" , "showByMail");
        long id_redactor = redactorRepository.findIdByMail(mail);
        model.addAttribute("newsByMail",newsRepository.findNewsByIdRedactor(id_redactor));
        model.addAttribute("space", "_____");
        return "newsPages/showNewsByMail";
    }
}