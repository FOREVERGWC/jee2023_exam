package cn.jee.exam.controller;

import cn.jee.exam.bean.Book;
import cn.jee.exam.bean.Category;
import cn.jee.exam.dao.BookDao;
import cn.jee.exam.dao.CategoryDao;
import cn.jee.exam.dao.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BaseController {
  @Autowired
  CategoryDao categoryDao;
  @Autowired
  BookDao bookDao;
  @Autowired
  ReaderDao readerDao;
  @RequestMapping("/")
  public String index(Model model){
    List<Category> categories = categoryDao.findAll();
    model.addAttribute("categories", categories);
    return "index";
  }

  @RequestMapping("/listBooks")
  public String listBooks(Model model, Integer categoryId){
    List<Book> books = bookDao.getBooksByCategoryId(categoryId);
    model.addAttribute("books", books);
    return "books";
  }
}
