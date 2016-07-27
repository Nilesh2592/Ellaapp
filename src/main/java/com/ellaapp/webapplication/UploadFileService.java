/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ellaapp.webapplication;

/**
 *
 * @author Nishantc
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.util.Date;
import javax.ws.rs.Produces;

@Path("/file")
public class UploadFileService {

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        String[] fileNameArray = fileDetail.getFileName().split("\\.");
        fileNameArray[0] = fileNameArray[0].concat("_trt_" + new Date().getTime() + ".");
        String newFileName = fileNameArray[0] + fileNameArray[1];

        String uploadedFileLocation = "d:\\uploaded\\" + newFileName;

        writeToFile(uploadedInputStream, uploadedFileLocation);
        String output = "{\"fileName\" : \"" + newFileName+"\"}";
//        "{\"message\":\"Username/Password invalid..\"}"

        return Response.status(200).entity(output).build();
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
