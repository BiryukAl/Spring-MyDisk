package ru.kpfu.itis.mydisk.domain.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.BeanWrapperImpl

class FieldMatchValidator : ConstraintValidator<FieldMatch, Any> {

    private lateinit var errorMessage: String
    private lateinit var firstFieldName: String
    private lateinit var secondFieldName: String

    override fun initialize(constraintAnnotation: FieldMatch?) {
        super.initialize(constraintAnnotation)
        errorMessage = constraintAnnotation!!.message
        firstFieldName = constraintAnnotation.first
        secondFieldName = constraintAnnotation.second
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        try {
            val bean = BeanWrapperImpl(value!!)
            val firstObj: Any? = bean.getPropertyValue(firstFieldName)
            val secondObj: Any? = bean.getPropertyValue(secondFieldName)
            val returnValue = firstObj == null && secondObj == null || firstObj != null && firstObj == secondObj
            //If the validation failed
            if (!returnValue) {
                context?.disableDefaultConstraintViolation()
                context?.buildConstraintViolationWithTemplate(errorMessage)?.addPropertyNode(secondFieldName)
                    ?.addConstraintViolation()
            }
            return returnValue
        } catch (ignore: Exception) {
        }
        return true
    }
}
