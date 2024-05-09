package cn.jee.exam.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {
  private Integer id;
  @NotBlank(message = "{reader.name.NotBlank}")
  private String name;

  private List<Integer> bookIds;
}
