package com.bongani;

import com.bongani.exceptions.ErrorDetails;
import com.bongani.exceptions.ErrorHandling;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(tags = "Directory")
public class Controller implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ErrorHandling.class);

    @RequestMapping(value="/api/directory/listing", produces = { "application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "Directory Listing")
    public List<Model> get(@RequestParam @NotEmpty int start, @RequestParam @NotEmpty int end) {
        String givenPath = ".";
        return getDirectoryListing(givenPath, start, end);
    }

    @RequestMapping("/error")
    public ResponseEntity<?> handleError() {
        ErrorDetails errorDetails = new ErrorDetails("NOT FOUND", 404);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private List<Model> getDirectoryListing(String pathName, int start, int end){
        List<Model> c = new ArrayList<Model>();

        int i = 0;
        String size;
        Path dir = FileSystems.getDefault().getPath( pathName );

        try(DirectoryStream<Path> stream = Files.newDirectoryStream( dir )) {
            for (Path path : stream) {
                if (i >= start && i <= end) {

                    String type;
                    BasicFileAttributes fileAtr = Files.readAttributes(path.toAbsolutePath(), BasicFileAttributes.class);
                    if (fileAtr.isDirectory()) {
                        type = "Directory";
                    } else {
                        type = "file";
                    }

                    size = fileAtr.size() + " KB";
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String createdAt = df.format(fileAtr.creationTime().toMillis());

                    Attribute attr = new Attribute(createdAt, path.getFileName().toString(), type, size);
                    c.add(new Model(path.toString(), attr));
                }
                else if(i > end){
                    break;
                }
                i++;

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return c;
    }

}
