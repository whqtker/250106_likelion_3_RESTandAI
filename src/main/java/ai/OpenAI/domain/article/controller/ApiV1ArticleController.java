package ai.OpenAI.domain.article.controller;

import ai.OpenAI.domain.article.dto.*;
import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.service.ArticleService;
import ai.OpenAI.domain.global.rsData.RsData;
import jakarta.validation.Valid;
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
    public RsData<ArticleWriteResponse> postArticle(@Valid @RequestBody ArticleWriteRequest articleWriteRequest) {
        Article article = articleService.write(articleWriteRequest.getTitle(), articleWriteRequest.getContent());
        ArticleDto articleDto = new ArticleDto(article);

        return RsData.of("200", "글 작성 성공", new ArticleWriteResponse(articleDto));
    }

    // Read
    @GetMapping
    public RsData<ArticlesResponse> getArticles() {
        List<Article> articles = articleService.findAll();

        List<ArticleDto> articleDtos = articles.stream()
                .map(ArticleDto::new)
                .toList();

        return RsData.of("200", "글 목록 조회 성공", new ArticlesResponse(articleDtos));
    }

    @GetMapping("/{id}")
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article article = articleService.findById(id).orElse(null);
        if(article == null) {
            return RsData.of("500", "글 조회 실패", null);
        }

        ArticleDto articleDto = new ArticleDto(article);
        return RsData.of("200", "글 조회 성공", new ArticleResponse(articleDto));
    }

    // Update
    @PatchMapping("/{id}")
    public RsData<ArticleDto> updateArticle(@PathVariable("id") Long id,
                                            @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.findById(id).orElse(null);
        if(article == null) {
            return RsData.of("500", "글 수정 실패: 글 찾지 못함", null);
        }

        Article modifiedArticle = this.articleService.modify(article,
                articleModifyRequest.getTitle(), articleModifyRequest.getContent());

        return RsData.of(
                "200",
                "게시글이 수정에 성공하였습니다.",
                new ArticleDto(modifiedArticle)
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    public RsData<Article> deleteArticle(@PathVariable("id") Long id) {
        Article article = articleService.delete(id);
        return RsData.of("200", "글 삭제 성공", article);
    }
}