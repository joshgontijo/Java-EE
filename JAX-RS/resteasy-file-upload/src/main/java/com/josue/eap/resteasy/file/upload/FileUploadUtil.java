/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.resteasy.file.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author Josue
 */
public class FileUploadUtil {

    public static final String FILE_FORM_PARAM = "file";
    private static final Logger log = Logger.getLogger(FileUploadUtil.class.getName());

    public List<FileUpload> parsePartFiles(MultipartFormDataInput input) {

        List<FileUpload> parsedFiles = new ArrayList<>();

        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inParts = formParts.get(FILE_FORM_PARAM);
        for (InputPart inputPart : inParts) {
            try {
                FileUpload fileUpload = new FileUpload();
                // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
                MultivaluedMap<String, String> headers = inputPart.getHeaders();

                fileUpload.setFileName(parseFileName(headers));
                fileUpload.setFileStream(inputPart.getBody(InputStream.class, null));

                parsedFiles.add(fileUpload);

            } catch (IOException e) {
                log.info(e.getMessage());
            }

        }
        return parsedFiles;

    }

    // Parse Content-Disposition header to get the original file name
    private String parseFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        for (String name : contentDispositionHeader) {

            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"", "");
                return fileName;
            }
        }
        return "randomName";
    }

//    // save uploaded file to a defined location on the server
//    private void saveFile(InputStream uploadedInputStream,
//            String serverLocation) {
//
//        try {
//            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            outpuStream = new FileOutputStream(new File(serverLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                outpuStream.write(bytes, 0, read);
//            }
//            outpuStream.flush();
//            outpuStream.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
}
