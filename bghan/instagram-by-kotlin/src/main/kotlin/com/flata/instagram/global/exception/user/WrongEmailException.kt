package com.flata.instagram.global.exception.user

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class WrongEmailException: ApplicationException(httpStatus = ExceptionType.WRONG_EMAIL_EXCEPTION.httpStatus, message = ExceptionType.WRONG_EMAIL_EXCEPTION.message)