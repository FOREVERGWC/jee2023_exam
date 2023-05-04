package cn.jee.exam.bean;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  public Category(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return id == category.id && categoryName.equals(category.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryName);
  }
}
