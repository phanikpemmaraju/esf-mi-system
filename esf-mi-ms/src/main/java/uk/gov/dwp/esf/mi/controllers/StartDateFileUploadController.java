package uk.gov.dwp.esf.mi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import uk.gov.dwp.esf.mi.services.StartDateUpdateService;

/**
 * @Author Phani Krishna
*/
@SuppressWarnings({"squid:S1166" })
@CrossOrigin(allowedHeaders = "*", methods = RequestMethod.POST, origins = "*")
@Controller
public class StartDateFileUploadController {
	
	@Autowired
	private StartDateUpdateService startDateService;
		
	private Logger logger = LoggerFactory.getLogger(this.getClass());	

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, path = "/start-date-file-upload" , consumes = { "multipart/form-data" })
	public ResponseEntity startDateFileUpload(@RequestParam("start-date-file") MultipartFile uploadFile) {
		logger.info(">>> File Name : <<< " + uploadFile.getOriginalFilename() + " ; size : " + uploadFile.getSize());

        if (!uploadFile.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

		long successful = 0; long failed = 0; long total =0;
		try(final CSVReader csvReader = new CSVReader(
				new BufferedReader(
						new InputStreamReader(uploadFile.getInputStream())))){
			
			final ExecutorService executor = Executors.newCachedThreadPool();
			
			long start = System.currentTimeMillis();
			
			final List<String[]> list = csvReader.readAll();		
			final List<String> errors = 
					CompletableFuture.supplyAsync(() ->
						list.stream()				
								.map(startDateService::processParticipant)
									.filter(startDateRecord -> startDateRecord.getStatus() == null)
										.map(startDateRecord -> startDateRecord.getNino())
											.collect(Collectors.toList()),executor).get();
										
			total = list.size() ; failed = errors.size() ; successful = total - failed;		
			
			logger.info("Time taken: " + (System.currentTimeMillis() - start) + "ms");
						
		} catch (NumberFormatException | IOException | InterruptedException | ExecutionException ex) {
			logger.info(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		logger.info("Total Records: " + total + " ; Successful: " + successful + " ; Failed: " + failed);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
