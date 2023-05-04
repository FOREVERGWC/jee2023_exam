package cn.jee.exam.controller;

import cn.jee.exam.bean.Book;
import cn.jee.exam.bean.Category;
import cn.jee.exam.bean.Reader;
import cn.jee.exam.dao.BookDao;
import cn.jee.exam.dao.CategoryDao;
import cn.jee.exam.dao.ReaderDao;
import cn.jee.exam.validator.BookValidation;
import com.alibaba.fastjson2.JSON;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
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

  @RequestMapping("/toUploadCoverImage")
  public String toUploadCoverImage(Model model, Integer bookId){
    model.addAttribute("bookId", bookId);
    return "upLoadCoverImage";
  }
  @PostMapping("/uploadCoverImage")
  @ResponseBody
  public String uploadCoverImage(MultipartFile coverImage,  int bookId) throws IOException {
    Map<String,String> res = new HashMap<>();
    if (coverImage.isEmpty()){
      res.put("msg","未选择封面图片");
      return JSON.toJSONString(res);
    }
    // 保存图片，路径d:\imgs\源名字
    String basePath="D:\\imgs\\";
    log.debug("源文件名称："+coverImage.getOriginalFilename());
    String coverImageUrl = basePath+coverImage.getOriginalFilename();

    coverImage.transferTo(new File(coverImageUrl));
    bookDao.upDateCoverImageUrl(bookId, coverImageUrl);
    res.put("imageUrl", coverImageUrl);
    return JSON.toJSONString(res);
  }

}
