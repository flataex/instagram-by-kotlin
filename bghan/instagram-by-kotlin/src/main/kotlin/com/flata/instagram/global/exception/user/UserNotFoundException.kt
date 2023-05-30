package com.flata.instagram.global.exception.user

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class UserNotFoundException : ApplicationException(httpStatus = ExceptionType.USER_NOT_FOUND_EXCEPTION.httpStatus, message = ExceptionType.USER_NOT_FOUND_EXCEPTION.message)