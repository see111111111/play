package com.lyl.play.utils;

public interface DataConverter<F, T> {
    T convert(F source);
}
