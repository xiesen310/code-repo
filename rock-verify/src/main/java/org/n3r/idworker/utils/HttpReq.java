package org.n3r.idworker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * @author 谢森
 */
public class HttpReq {
    private final String baseUrl;
    private String req;
    private StringBuilder params = new StringBuilder();
    public final char c = '&';
    public final int OK_STATUS = 200;

    Logger logger = LoggerFactory.getLogger(HttpReq.class);


    public HttpReq(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static HttpReq get(String baseUrl) {
        return new HttpReq(baseUrl);
    }

    public HttpReq req(String req) {
        this.req = req;
        return this;
    }

    public HttpReq param(String name, String value) {
        if (params.length() > 0) {
            params.append(c);
        }
        try {
            params.append(name).append('=').append(URLEncoder.encode(value, Constants.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public String exec() {
        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection) new URL(baseUrl
                    + (req == null ? "" : req)
                    + (params.length() > 0 ? ("?" + params) : "")).openConnection();
            http.setRequestProperty(Constants.ACCEPT_CHARSET, Constants.UTF_8);
            HttpURLConnection.setFollowRedirects(false);
            http.setConnectTimeout(5 * 1000);
            http.setReadTimeout(5 * 1000);
            http.connect();

            int status = http.getResponseCode();
            String charset = getCharset(http.getHeaderField(Constants.CONTENT_TYPE));

            if (status == OK_STATUS) {
                return readResponseBody(http, charset);
            } else {
                logger.warn("non 200 respoonse :" + readErrorResponseBody(http, status, charset));
                return null;
            }
        } catch (Exception e) {
            logger.error("exec error {}", e.getMessage());
            return null;
        } finally {
            if (http != null) {
                http.disconnect();
            }
        }

    }

    private static String readErrorResponseBody(HttpURLConnection http, int status, String charset) throws IOException {
        InputStream errorStream = http.getErrorStream();
        if (errorStream != null) {
            String error = toString(charset, errorStream);
            return ("STATUS CODE =" + status + "\n\n" + error);
        } else {
            return ("STATUS CODE =" + status);
        }
    }

    private static String readResponseBody(HttpURLConnection http, String charset) throws IOException {
        InputStream inputStream = http.getInputStream();

        return toString(charset, inputStream);
    }

    private static String toString(String charset, InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }

        return new String(baos.toByteArray(), charset);
    }

    private static String getCharset(String contentType) {
        if (contentType == null) {
            return Constants.UTF_8;
        }

        String charset = null;
        for (String param : contentType.replace(Constants.STR1, Constants.STR2).split(Constants.COMMA)) {
            if (param.startsWith(Constants.CHARSET_STR)) {
                charset = param.split(Constants.EQUAL_SIGN, 2)[1];
                break;
            }
        }

        return charset == null ? Constants.UTF_8 : charset;
    }
}
