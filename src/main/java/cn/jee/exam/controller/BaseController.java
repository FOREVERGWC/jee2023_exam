package cn.jee.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
  @RequestMapping("/")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    return mv;
  }

  @RequestMapping("/books")
  public ModelAndView books(Integer categoryId) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("categoryId", categoryId);
    mv.setViewName("books");
    return mv;
  }

  @RequestMapping("/readers")
  public ModelAndView readers(Integer bookId) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("bookId", bookId);
    mv.setViewName("readers");
    return mv;
  }
}
