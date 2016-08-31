package com.softserve.osbb.service.utils;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
public class HttpResponseMessageBuilder {

    static class MediaTypeConstants {
        static final String TYPE_JSON = "application/json";
        static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
        static final String MEDIA_TYPE_PDF = "application/pdf";
        static final String MEDIA_TYPE_CSV = "text/csv";

    }


    static String returnContentTypeOf(String fileName) {
        switch (fileName.substring(fileName.lastIndexOf(".") + 1)) {
            case "pdf":
                return MediaTypeConstants.MEDIA_TYPE_PDF;
            case "xls":
                return MediaTypeConstants.MEDIA_TYPE_EXCEL;
            case "csv":
                return MediaTypeConstants.MEDIA_TYPE_CSV;
            default:
                return MediaTypeConstants.TYPE_JSON;
        }
    }

}
