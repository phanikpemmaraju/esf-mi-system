package uk.gov.dwp.esf.mi.controllers;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import uk.gov.dwp.esf.mi.common.StartDateRecord;
import uk.gov.dwp.esf.mi.ms.MockDomainConfiguration;
import uk.gov.dwp.esf.mi.services.StartDateUpdateService;

/**
 * @Author Phani Krishna
*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { StartDateFileUploadController.class, MockDomainConfiguration.class })
@WebAppConfiguration
public class StartDateFileUploadControllerTest {
	
	@Autowired
	private StartDateUpdateService startDateService;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	private SimpleDateFormat dateFormat;	
	private MockMultipartFile file;


	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		file = new MockMultipartFile("start-date-file","participants.csv","multipart/form-data","SJ196058B,400119,2016-04-25".getBytes());
	}
	
	@After
	public void resetMocks() {
		reset(startDateService);
	}
	
	
	/*
	 * Test startDateFileUpload controller rest method.
	 * This method updates the participants details with the start date, updated date, proposed exit date and record state.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testStartDateFileUpload() throws Exception {
		StartDateRecord startDateRecord = new StartDateRecord();
		startDateRecord.setNino("SJ196058B");startDateRecord.setContractId(400119);startDateRecord.setStartDate(dateFormat.parse("2016-04-25"));
		
		when(startDateService.processParticipant(Mockito.anyObject())).thenReturn(startDateRecord);
		
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                 .file(file))
                 .andExpect(status().isCreated());
		
		startDateRecord.setStatus("Success");
		
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                .file(file))
                .andExpect(status().isCreated());
		
		when(startDateService.processParticipant(Mockito.anyObject())).thenThrow(NumberFormatException.class);
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                .file(file))
                .andExpect(status().isBadRequest());
		
	}
	
	/*
	 * Test startDateFileUpload controller rest method for invalid file format.
	 */
	@Test
	public void testStartDateFileUploadForInvalidFileFormat() throws Exception {
		final String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"+
								"<Participant id=\"1\"><nino>SL123457B</nino></Participant>";

		final MockMultipartFile xmlFileFormat = new MockMultipartFile("start-date-file","participants.xml","multipart/form-data",xmlContent.getBytes());
		
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                .file(xmlFileFormat))
                .andExpect(status().isBadRequest());
		
	}
	
	
	/*
	 * Test startDateFileUpload controller rest method for IOException.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testStartDateFileUploadIOException() throws Exception {
		when(startDateService.processParticipant(Mockito.anyObject())).thenThrow(IOException.class);
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                .file(file))
                .andExpect(status().isBadRequest());		
	}

	/*
	 * Test startDateFileUpload controller rest method for InterruptedException.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testStartDateFileUploadInterruptedException() throws Exception {
		when(startDateService.processParticipant(Mockito.anyObject())).thenThrow(InterruptedException.class);
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                .file(file))
                .andExpect(status().isBadRequest());		
	}

	
	/*
	 * Test startDateFileUpload controller rest method for ExecutionException.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testStartDateFileUploadExecutionException() throws Exception {
		when(startDateService.processParticipant(Mockito.anyObject())).thenThrow(ExecutionException.class);
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/start-date-file-upload")
                .file(file))
                .andExpect(status().isBadRequest());		
	}

}
