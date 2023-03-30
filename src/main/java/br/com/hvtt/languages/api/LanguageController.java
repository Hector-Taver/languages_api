package br.com.hvtt.languages.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
  @GetMapping(value = "/preferred-language")
  public String processPreferredLanguage() {
    return "Olá, Java!";
  }
}
