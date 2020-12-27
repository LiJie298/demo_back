package com.sebc.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static Logger infoLogger = LoggerFactory.getLogger("info");
    private static Logger errorLogger = LoggerFactory.getLogger("error");

    public static void info(String message) {
        infoLogger.info(message);
    }

    public static void warn(String message) {
        infoLogger.info(message);
    }

    public static void error(String message, Exception e) {
        errorLogger.error(message, e);
    }

}
