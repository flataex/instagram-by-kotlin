package com.flata.instagram.global.exception.user

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class WrongPasswordException: ApplicationException(httpStatus = ExceptionType.WRONG_PASSWORD_EXCEPTION.httpStatus, message = ExceptionType.WRONG_PASSWORD_EXCEPTION.message)