package com.flata.instagram.global.exception.feed

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class FeedNotFoundException : ApplicationException(httpStatus = ExceptionType.FEED_NOT_FOUND_EXCEPTION.httpStatus, message = ExceptionType.FEED_NOT_FOUND_EXCEPTION.message)