package cn.jee.exam.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
  private Integer id;
  @NotBlank(message = "{book.name.NotBlank}")
  private String name;
  @Min(value = 0, message = "{book.price.Min}")
  private BigDecimal price;
  @Length(min = 10, max = 100, message = "{book.profile.Length}")
  private String profile;

  private String cover;
  @NotNull(message = "{book.categoryId.NotNull}")
  private Integer categoryId;
}
