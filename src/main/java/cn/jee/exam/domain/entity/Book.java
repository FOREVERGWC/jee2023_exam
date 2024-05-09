package cn.jee.exam.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private BigDecimal price;

  private String profile;

  private String cover;

  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  @JsonIgnoreProperties(value = {"books"})
  private Category category;

  @ManyToMany(mappedBy = "books")
  @ToString.Exclude
  @JsonIgnoreProperties(value = {"books"})
  private List<Reader> readers;

  public Book(Integer id) {
    this.id = id;
  }
}
