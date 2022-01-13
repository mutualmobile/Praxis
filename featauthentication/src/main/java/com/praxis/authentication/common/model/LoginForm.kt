package com.praxis.authentication.common.model

import com.praxis.authentication.common.model.FailureType.EMAIL_NOT_VALID
import com.praxis.authentication.common.model.FailureType.PASSWORD_NOT_VALID
import com.praxis.authentication.login.services.IValidate
import com.praxis.authentication.login.exceptions.FormValidationException
import java.util.regex.Pattern


data class LoginForm(val email: String = "", var password: String = "") : IValidate {
  override fun validate(): Boolean {
    val pattern = emailRegex()
    if (!pattern.matcher(email).matches()) {
      throw FormValidationException(EMAIL_NOT_VALID)
    }

    if (password.length < 6) {
      throw FormValidationException(PASSWORD_NOT_VALID)
    }

    return true
  }

  private fun emailRegex() = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
  )
}
