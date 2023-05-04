package cn.jee.exam.validator;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
@Getter
@Setter

public class BookValidation {
  private Integer categoryId;
  @NotBlank(message = "{bookName.notNull}")
  private String bookName;

  @Min(value = 0,message = "{price.min}")
  private Double price;

  @Length(min = 20, max = 100,message = "{profile.length}")
  private String profile;


}
