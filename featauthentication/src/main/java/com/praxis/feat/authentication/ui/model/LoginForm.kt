package com.praxis.feat.authentication.ui.model

import com.praxis.feat.authentication.services.Validatable
import android.util.Patterns
import com.praxis.feat.authentication.ui.exceptions.FormValidationFailed


data class LoginForm(val email: String = "", var password: String = "") : Validatable {
  override fun validate(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    if (!pattern.matcher(email).matches()) {
      throw FormValidationFailed(FailureType.EMAIL_NOT_VALID)
    }

    if (password.length < 6) {
      throw FormValidationFailed(FailureType.PASSWORD_NOT_VALID)
    }

    return true
  }
}
