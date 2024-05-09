package cn.jee.exam.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.jee.exam.domain.R;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
  @Value("${upload.path}")
  private String basePath;

  @PostMapping("/upload")
  public R upload(MultipartFile file) {
    if (file.isEmpty()) {
      return R.error("禁止上传空文件！");
    }
    String suffixName = "." + FileUtil.extName(file.getOriginalFilename());
    String fileName = UUID.randomUUID() + suffixName;
    try {
      Path filePath = Paths.get(basePath, fileName);
      Files.createDirectories(filePath.getParent());
      FileUtil.copyFile(file.getInputStream(), filePath.toFile());
      return R.success().put("path", basePath + fileName);
    } catch (IOException e) {
      return R.error("文件上传失败");
    }
  }

  @SneakyThrows
  @GetMapping("/preview")
  public void preview(@RequestParam(value = "fileName") String fileName, HttpServletResponse response) {
    if (StrUtil.isNotEmpty(fileName) && !Objects.equals(fileName, "null")) {
      try (OutputStream outputStream = response.getOutputStream()) {
        IoUtil.copy(new FileInputStream(fileName), outputStream, IoUtil.DEFAULT_BUFFER_SIZE);
      }
    }
  }
}
