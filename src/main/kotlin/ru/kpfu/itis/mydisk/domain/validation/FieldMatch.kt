package ru.kpfu.itis.mydisk.domain.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [FieldMatchValidator::class])
@MustBeDocumented
annotation class FieldMatch(
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],

    val first: String,
    val second: String,
    val message: String = "{constraints.field-match}",

    ) {
    @Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    @MustBeDocumented
    annotation class List(vararg val value: FieldMatch)
}
