package com.flata.instagram.global.exception.comment

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ExceptionType

class CommentNotFoundException : ApplicationException(httpStatus = ExceptionType.COMMENT_NOT_FOUND_EXCEPTION.httpStatus, message = ExceptionType.COMMENT_NOT_FOUND_EXCEPTION.message)