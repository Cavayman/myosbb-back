package com.softserve.osbb.service.utils;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by nazar.dovhyy on 09.08.2016.
 */
@Service
public class DropBoxFileServer {

    private String app_key;
    private String app_secret;
    private DbxClientV2 dbxClient;
    private final String propertiesFileLocation = "/drop_box_access.properties";
    private static final String ACCESS_TOKEN = "derDZ_RusWAAAAAAAAAAR6DovCGbfG_XMpAt1sD-S2lyL3G0-_HCSEdaM16lnNoT";
    private final Logger logger = LoggerFactory.getLogger(DropBoxFileServer.class);


    public DropBoxFileServer() {
    }

    private void readAccessDropBoxPropertyConfig() throws IOException {
        Properties properties = new Properties();
        try {
            logger.info("loading access config properties");
            InputStream propertyFileInputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileLocation);
            properties.load(propertyFileInputStream);
            app_key = properties.getProperty("drop_box_app_key");
            app_secret = properties.getProperty("drop_box_app_sercret");
        } catch (IOException e) {
            logger.error("exception while reading access config properties");
            throw new IOException(e);
        }
    }


    public void authenticate() throws IOException, DbxException {
        //readAccessDropBoxPropertyConfig();
        logger.info("initializing authentication process");
        DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("osbb", Locale.getDefault().toString());
        dbxClient = new DbxClientV2(dbxRequestConfig, ACCESS_TOKEN);
        String displayAccountName = dbxClient.users().getCurrentAccount().getName().getDisplayName();
        logger.info("account info: " + displayAccountName);
    }


    public String uploadFile(String filePath, String type) throws IOException, DbxException {
        logger.info("starting file upload");
        File inputFile = new File(filePath);
        String sharedUrl = "";
        try (InputStream is = new FileInputStream(inputFile)) {
            String outputFilePath = "/report" + UUID.randomUUID().toString() + "." + type;
            FileMetadata fileMetadata = dbxClient.files()
                    .uploadBuilder(outputFilePath)
                    .uploadAndFinish(is);
            sharedUrl = dbxClient.sharing()
                    .createSharedLinkWithSettings(outputFilePath)
                    .getUrl()
                    .toString();
            logger.info("shared URL " + sharedUrl);
        }
        logger.info("finished file upload to  server " + sharedUrl);

        return sharedUrl;
    }


    public void downloadFile(String fileName) throws IOException, DbxException {

    }
}
