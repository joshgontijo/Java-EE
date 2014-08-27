/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.resteasy.file.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * REST Web Service
 *
 * @author cit
 */
@Path("data")
public class DataResource {

    @Context
    private UriInfo context;
    private static final Logger LOG = Logger.getLogger(DataResource.class.getName());

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(MultipartFormDataInput input, @Context HttpHeaders headers) {

        for (String header : headers.getRequestHeaders().keySet()) {
            LOG.log(Level.INFO, "{0} -> {1}", new Object[]{header, headers.getRequestHeaders().getFirst(header)});
        }

        List<FileUpload> files = new FileUploadUtil().parsePartFiles(input);

        for (FileUpload fu : files) {
            try {
                File dest = new File(System.getProperty("jboss.server.data.dir") + "/" + fu.getFileName());
                FileUtils.copyInputStreamToFile(fu.getFileStream(), dest);
            } catch (IOException ex) {
                Logger.getLogger(DataResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "OK";

    }

}
