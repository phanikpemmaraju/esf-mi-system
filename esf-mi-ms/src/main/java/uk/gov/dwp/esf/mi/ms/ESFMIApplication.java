package uk.gov.dwp.esf.mi.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Phani Krishna
*/

@SuppressWarnings({ "squid:S2095","squid:S1118" })
@SpringBootApplication(scanBasePackages="uk.gov.dwp.esf")
public class ESFMIApplication {
			
    public static void main( String[] args )
    {
    	 SpringApplication.run(ESFMIApplication.class, args);
    }
    
}
