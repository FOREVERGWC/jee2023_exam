package cn.jee.exam.bean;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String categoryName;

  @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "category")
  @ToString.Exclude
  private List<Book> books = new ArrayList<>();

  public Category(String categoryName) {
    this.categoryName = categoryName;
  }
}
