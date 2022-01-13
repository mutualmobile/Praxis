package com.praxis.authentication.login.exceptions

import com.praxis.authentication.common.model.FailureType

class FormValidationException(val failType: FailureType) : Throwable()
