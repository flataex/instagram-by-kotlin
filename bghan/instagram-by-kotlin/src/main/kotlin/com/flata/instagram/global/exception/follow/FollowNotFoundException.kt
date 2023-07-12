package com.flata.instagram.global.exception.follow

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class FollowNotFoundException : ApplicationException(httpStatus = ExceptionType.FOLLOW_NOT_FOUND_EXCEPTION.httpStatus, message = ExceptionType.FOLLOW_NOT_FOUND_EXCEPTION.message)