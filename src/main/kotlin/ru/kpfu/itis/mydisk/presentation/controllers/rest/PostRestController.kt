package ru.kpfu.itis.mydisk.presentation.controllers.rest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.mydisk.domain.services.PostService
import ru.kpfu.itis.mydisk.presentation.mapper.ToResponse
import ru.kpfu.itis.mydisk.presentation.model.PostResponse

@RestController
class PostRestController(
    private val postService: PostService,
    private val mapper: ToResponse,
) {

    @GetMapping("/api/v1/posts")
    @Operation(
        summary = "Get all categories (paginated)",
        responses = [ApiResponse(responseCode = "200", description = "Category page returned")]
    )
    fun getAllPost(
        @PageableDefault
        @ParameterObject
        pageable: Pageable,
    ): List<PostResponse> {
        return postService.getAllPost(pageable = pageable)
            .toList().map(mapper::toPostResponse)
    }

    // TODO: https://github.com/springdoc/springdoc-openapi/issues/2203
    // TODO: https://github.com/springdoc/springdoc-openapi/issues/2235

}
