package com.flata.instagram.global.exception.like

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class LikeNotFoundException : ApplicationException(httpStatus = ExceptionType.LIKE_NOT_FOUND_EXCEPTION.httpStatus, message = ExceptionType.LIKE_NOT_FOUND_EXCEPTION.message)