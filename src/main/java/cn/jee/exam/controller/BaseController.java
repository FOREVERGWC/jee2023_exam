package cn.jee.exam.controller;

import cn.jee.exam.bean.Book;
import cn.jee.exam.bean.Category;
import cn.jee.exam.dao.BookDao;
import cn.jee.exam.dao.CategoryDao;
import cn.jee.exam.dao.ReaderDao;
import cn.jee.exam.validator.BookValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @RequestMapping("/toAddBook")
  public String toAddBook(Model model, Integer categoryId, BookValidation bookValidation){
    model.addAttribute("categoryId", categoryId);
    return "add_book";
  }
  @PostMapping("/addBook")
  public String addBook(Model model, @Valid BookValidation bookValidation, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
      return "add_book";
    }
    Book book = new Book(bookValidation.getBookName(), bookValidation.getPrice(), bookValidation.getProfile());
    Category category = new Category(bookValidation.getCategoryId());
    book.setCategory(category);
    int id = bookDao.save(book).getId();
    if (id != 0){
      return "redirect:/listBooks?categoryId="+category.getId();
    }else {
      model.addAttribute("msg","添加图书失败");
      return "error";
    }
  }
}
