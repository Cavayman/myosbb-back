package com.softserve.osbb.service.utils;

import com.softserve.osbb.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    public String getOutputFileDirectory(final String subDir) throws IOException {
        Path outputFilePath = Paths.get(subDir + File.separator + dateFormatter.format(new Date()));
        outputFilePath = Files.createDirectories(outputFilePath);
        String outputFileDirectory = outputFilePath.toAbsolutePath().toString();
        logger.info("save file to server directory: " + outputFileDirectory);
        return outputFileDirectory;
    }
}
