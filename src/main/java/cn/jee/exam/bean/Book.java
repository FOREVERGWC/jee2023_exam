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
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String bookName;

  private Double price;

  private String profile;

  private String coverImageUrl;

  @ManyToOne
  @ToString.Exclude
  private Category category;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Reader> readers = new ArrayList<>();

  public Book(String bookName, Double price, String profile, String coverImageUrl) {
    this.bookName = bookName;
    this.price = price;
    this.profile = profile;
    this.coverImageUrl = coverImageUrl;
  }
}
