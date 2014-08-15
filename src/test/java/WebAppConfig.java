import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


@Configuration
@Import({ControllerConfig.class })
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ControllerConfig controllerConfig;



    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.APPLICATION_JSON));
        converters.add(stringConverter);
        converters.add(new CsvMessageConverter());
        MappingJackson2HttpMessageConverter jsonMessageConvert = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = controllerConfig.objectMapper();
        jsonMessageConvert.setObjectMapper(objectMapper);
        converters.add(jsonMessageConvert);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }


}
