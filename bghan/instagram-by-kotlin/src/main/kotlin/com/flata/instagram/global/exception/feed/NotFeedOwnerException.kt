package com.flata.instagram.global.exception.feed

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class NotFeedOwnerException : ApplicationException(httpStatus = ExceptionType.NOT_FEED_OWNER_EXCEPTION.httpStatus, message = ExceptionType.NOT_FEED_OWNER_EXCEPTION.message)