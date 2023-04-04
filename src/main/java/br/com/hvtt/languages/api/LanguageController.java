package br.com.hvtt.languages.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LanguageController {
  @Autowired
  private LanguageRepository repository;

  @GetMapping("/languages")
  public List<Language> getLanguages() {
    List<Language> languages = repository.findAll();

    return languages;
  }

  @GetMapping("/languages/{id}")
  public Language getLanguageById(@PathVariable String id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/languages")
  public Language registerLanguage(@RequestBody Language language) {
    Language registeredLanguage = repository.save(language);

    return registeredLanguage;
  }
}
