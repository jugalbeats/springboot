package com.example.jugalbeats.controllers;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
/*
 * dhruv:2021
 * */
@RestController
public class DriveController {

	   private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	    private static final String TOKENS_DIRECTORY_PATH = "tokens";

	    /**
	     * Global instance of the scopes required by this quickstart.
	     * If modifying these scopes, delete your previously saved tokens/ folder.
	     */
	    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	    /**
	     * Creates an authorized Credential object.
	     * @param HTTP_TRANSPORT The network HTTP Transport.
	     * @return An authorized Credential object.
	     * @throws IOException If the credentials.json file cannot be found.
	     */
	    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
	        // Load client secrets.
	        InputStream in = DriveController.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

	        // Build flow and trigger user authorization request.
	        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	                .setAccessType("offline")
	                .build();
	        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9009).build();
	        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    }

    @PostMapping("/upload")
    public ResponseEntity<String> singleFileUpload(@RequestParam("file") MultipartFile[] file,
                                   RedirectAttributes redirectAttributes) throws IOException, GeneralSecurityException {
    	 final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	 List<String>parentFolder=new ArrayList<String>();
    	 parentFolder.add("1sUKEGbp7TTfMNtLoxZQck_jXYMHMdCGl");
         Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                 .setApplicationName(APPLICATION_NAME)
                 .build();
         for(MultipartFile files: file) {
        if (files.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return new ResponseEntity<>("error uploading",HttpStatus.BAD_REQUEST);
        }
         }
         for(MultipartFile files: file) {
 		File fileMetadata = new File();
        fileMetadata.setName(files.getOriginalFilename());
        fileMetadata.setParents(parentFolder);
//        java.io.File filePath = new java.io.File(files.getOriginalFilename());
        java.io.File main=convertMultiPartToFile(files);
        FileContent mediaContent = new FileContent(files.getContentType(), main);
        File f = service.files().create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();
         
        return new ResponseEntity<>("https://drive.google.com/uc?export=view&id="+f.getId(),HttpStatus.OK);
         }
/*        redirectAttributes.addFlashAttribute("message","drive.google.com/open?id=" + f.getId() + "");*/
         redirectAttributes.addFlashAttribute("message","Upload sucessfull:https://drive.google.com/drive/u/1/my-drive");
         return new ResponseEntity<>("Upload status not sure",HttpStatus.OK);
    }
    private static java.io.File convertMultiPartToFile(MultipartFile file) throws IOException {
    	java.io.File convFile = new java.io.File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convFile)){
			fos.write(file.getBytes());
			return convFile;
		}
	}

}