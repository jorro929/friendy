package ru.grinin.friendy.back;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

public record IncomingHttpRequest(HttpMethod method,
                                  Map<String, String> params,
                                  String uri,
                                  String version,
                                  Map<String, String> headers,
                                  String body) {

}
