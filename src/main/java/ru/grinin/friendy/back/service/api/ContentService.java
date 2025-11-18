package ru.grinin.friendy.back.service.api;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface ContentService {

    void upload(String contentPath, InputStream inputStream) throws IOException;

    void download(String contentPath, ServletOutputStream outputStream) throws IOException;
}
