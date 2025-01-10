package ai.OpenAI.domain.article.controller;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.service.ArticleService;
import ai.OpenAI.domain.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    // Create
    @PostMapping
    public RsData<Article> postArticle(@RequestBody Article article) {
        return articleService.write(article.getAuthor().getId(), article.getTitle(), article.getContent());
    }

    // Read
    @GetMapping
    public RsData<List<Article>> getArticles() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public RsData<Article> getArticle(@PathVariable("id") Long id) {
        return articleService.findById(id);
    }

    // Update
    @PatchMapping("/{id}")
    public RsData<Article> patchArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        return this.articleService.modify(article, article.getTitle(), article.getContent());
    }

    // Delete
    @DeleteMapping("/{id}")
    public RsData<Article> deleteArticle(@PathVariable("id") Long id) {
        return articleService.delete(id);
    }
}
