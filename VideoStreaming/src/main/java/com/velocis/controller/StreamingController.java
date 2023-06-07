package com.velocis.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.velocis.model.ResponseModel;
import com.velocis.model.UploadFileResponse;
import com.velocis.service.FileStorageService;
import com.velocis.service.StreamingService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
public class StreamingController {

	@Autowired
	private StreamingService service;
    @Autowired
    private FileStorageService fileStorageService;
    
    

   //not working for server
	@RequestMapping(value = "/upload/remote", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

		//File path = new File("D://LocalDocument/"+ file.getOriginalFilename());
		File path = new File("/root/GitHub/Docs/"+ file.getOriginalFilename());
			//File outputText = new File(path.getParentFile(), "Decrypted.txt");	
		path.createNewFile();
		try (FileOutputStream output = new FileOutputStream(path)) {//try with resources
			output.write(file.getBytes());
			output.close();
			return new ResponseEntity<>("File is uploaded successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
		}
	}

	 // working for server
	@RequestMapping(value = "/upload/remotetest", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public UploadFileResponse uploadFileTest(@RequestParam("file") MultipartFile file) throws IOException {
		 String fileName = fileStorageService.storeFile(file);
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();

	        return new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	}

	@GetMapping(value = "video/{title}", produces = "video/mp4")
	public Mono<Resource> getVideos(@PathVariable String title) throws InterruptedException {
		return service.getVideo(title);
		
	}
	
	@GetMapping(value = "videofolder/{title}", produces = "video/mp4")
	public Mono<Resource> getVideoFromFolder(@PathVariable String title) throws IOException {
		
		return service.getVideoFromFolder(title);
		
		}
		

	@GetMapping(value = "musicfolder/{title}", produces = "audio/mp3")
	public Mono<Resource> getMp3FromFolder(@PathVariable String title) throws IOException {
		
		return service.getMp3FromFolder(title);
		
		}
	
	
	@GetMapping("get-media-files")
	public ResponseEntity<ResponseModel> getAllFiles() throws IOException {
		ResponseModel rs=new ResponseModel();
		List<String> list= service.getAllFiles();
		rs.setData(list);
		rs.setMessage("Fetch data successfully.");
		rs.setStatus("200");
		return ResponseEntity.ok(rs);
		
		}
	
	
		
}
