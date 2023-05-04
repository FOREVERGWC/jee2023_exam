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
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToMany(mappedBy = "readers")
  @ToString.Exclude
  private List<Book> performers = new ArrayList<>();

  public Reader(String name) {
    this.name = name;
  }
}
