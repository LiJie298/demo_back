package com.sebc.api.service;

import org.bson.types.ObjectId;

import java.io.InputStream;

public interface VideoFileService {
    ObjectId saveFile(String fileName,String contentType, InputStream inputStream);
}
