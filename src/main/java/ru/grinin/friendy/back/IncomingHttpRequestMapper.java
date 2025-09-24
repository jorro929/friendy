package ru.grinin.friendy.back;

import java.lang.reflect.Method;
import java.util.*;

public class IncomingHttpRequestMapper {


    private String method;
    private Map<String, String> params;
    private String uri;
    private String version;
    private Map<String, String> headers;
    private StringBuilder body;

    public IncomingHttpRequest mapRequest(String request) {
        clear();

        try (Scanner scanner = new Scanner(request)) {
            String line = scanner.nextLine();

            method = line.split("/")[0].trim();
            getParamsAndUri(line);
            version = line.split(" HTTP/")[1].trim();

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                } else {
                    headers.put(line.split(":")[0], line.split(":")[1]);
                }
            }


            while (scanner.hasNextLine()) {
                body.append(scanner.nextLine()).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }


        return new IncomingHttpRequest(HttpMethod.valueOf(method),
                params,
                uri,
                version,
                headers,
                body.toString().trim());

    }

    private void clear() {
        method = null;
        params = new HashMap<>();
        uri = null;
        version = null;
        headers = new HashMap<>();
        body = new StringBuilder();
    }

    private void getParamsAndUri(String fistLine) {
        params = new HashMap<>();
        String[] resultLine = fistLine.split(" HTTP/")[0]
                .split(method)[1]
                .split("\\?");
        uri = resultLine[0].trim();
        if (resultLine.length != 2) return;

        for (String param : resultLine[1].split("&")) {
            if (param.split("=").length < 2) {
                params.put(param.split("=")[0], "");
            } else {
                params.put(param.split("=")[0], param.split("=")[1]);
            }
        }


    }
}
