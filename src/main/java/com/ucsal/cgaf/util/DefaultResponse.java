package com.ucsal.cgaf.util;

public record DefaultResponse<T> (Integer status, String message, T data) {
}