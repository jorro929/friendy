package ru.grinin.friendy.back;

public record ServerHttpResponse (byte[] startLine,
                                  byte[] headers,
                                  byte[] body){
}
