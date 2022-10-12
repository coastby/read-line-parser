package com.line.parser;

public interface Parser<T> {
    //어떤 파일을 파싱하는 지에 따라 return 타입이 다르다.
    T parse(String str);
}
