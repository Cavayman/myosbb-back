package com.softserve.osbb.service.utils;

import com.softserve.osbb.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nazar.dovhyy on 20.08.2016.
 */
@Component
public class FileServer {

    private static Logger logger = LoggerFactory.getLogger(FileServer.class);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);

    @Value("${app.fileServer}")
    private String fileServer;

    @Value("${app.filepath}")
    private String filePath;

    public String getFileServer() {
        return fileServer;
    }

    public String getFilePath() {
        return filePath;
    }

    @PostConstruct
    public void show() {
        logger.info("fileServer: " + fileServer);
        logger.info("filePath: " + filePath);
    }

    public String getOutputFileDirectory(final String subDir) {
        File outputFileDir = new File(filePath + File.separator + subDir +
                File.separator + dateFormatter.format(new Date()));
        if (!outputFileDir.exists()) {
            outputFileDir.mkdir();
        }
        String outputFilePath = outputFileDir.getAbsolutePath();
        logger.info("save file to server directory: " + outputFilePath);
        return outputFilePath;
    }
}
