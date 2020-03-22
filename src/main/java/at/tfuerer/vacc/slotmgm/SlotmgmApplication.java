package at.tfuerer.vacc.slotmgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.spring.annotation.EnableVaadin;

import de.zilchinger.vatsimdataapi.VatsimDataManager;

@SpringBootApplication
@EnableVaadin
@EnableCaching
@EnableScheduling
public class SlotmgmApplication {

  public static void main(String[] args) {
    SpringApplication.run(SlotmgmApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.rootUri("http://api.vateud.net").build();
  }

  @Bean
  public VatsimDataManager vatsimDataMgr() {
    VatsimDataManager vatsimDataManager = new VatsimDataManager();
    vatsimDataManager.init("http://status.vatsim.net/status.txt");
    return vatsimDataManager;
  }
}
