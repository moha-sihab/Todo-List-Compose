package com.mohasihab.todolistcompose.core.domain.mapper

import com.mohasihab.todolistcompose.core.utils.ResultState

fun <R> responseErrorToResultStateError(
    error: Throwable,
): ResultState<R> {
    return ResultState.Error(message = error.message.toString())
}