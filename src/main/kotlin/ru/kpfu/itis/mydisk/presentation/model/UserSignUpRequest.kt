package ru.kpfu.itis.mydisk.presentation.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import ru.kpfu.itis.mydisk.domain.validation.FieldMatch

@FieldMatch.List(
    FieldMatch(first = "password", second = "repeatPassword", message = "The password fields must match")
)
data class UserSignUpRequest(

    @field:[Size(min = 1, message = "Short name")]
    val name: String?,
    @field:[NotBlank
    Email(message = "Incorrect email")
    ]
    val email: String?,
    @field: [
    Size(min = 5, message = "Weak password")
    NotBlank(message = "Enter password")
    ]
    val password: String?,
    @field: NotNull
    val repeatPassword: String?,
)