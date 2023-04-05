package br.com.hvtt.languages.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LanguageController {
  @Autowired
  private LanguageRepository repository;

  @GetMapping("/languages")
  public List<Language> getLanguages() {
    return repository.findByOrderByRanking();
  }

  @GetMapping("/languages/{id}")
  public Language getLanguageById(@PathVariable String id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/languages")
  public ResponseEntity<Language> registerLanguage(@RequestBody Language language) {
    return new ResponseEntity<>(repository.save(language), HttpStatus.CREATED);
  }

  @PutMapping("/languages/{id}")
  public Language updateLanguage(@PathVariable String id, @RequestBody Language language) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    language.setId(id);
    return repository.save(language);
  }

  @DeleteMapping("/languages/{id}")
  public void deleteLanguage(@PathVariable String id) {
    repository.deleteById(id);
  }
}
