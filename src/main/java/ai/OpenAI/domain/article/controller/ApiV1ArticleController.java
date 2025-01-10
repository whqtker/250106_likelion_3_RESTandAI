package ai.OpenAI.domain.article.controller;

import ai.OpenAI.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    // Create
    @PostMapping
    public void postArticle() {
        articleService.write(1L, "title", "content");
    }


    // Read
    @GetMapping
    public void getArticles() {
        articleService.findAll();
    }

    @GetMapping("/{id}")
    public void getArticle(@PathVariable("id") Long id) {
        articleService.findById(id);

    }

    // Update
    @PatchMapping("/{id}")
    public void patchArticle(@PathVariable("id") Long id) {
        articleService.modify(null, "title", "content");
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.findById(id);
    }
}
