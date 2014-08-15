import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CsvMessageConverter extends AbstractHttpMessageConverter<CsvResponse> {

    public CsvMessageConverter() {
        super(new MediaType("text", "csv", StandardCharsets.UTF_8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz != null && CsvResponse.class.isAssignableFrom(clazz);
    }

    @Override
    protected CsvResponse readInternal(Class<? extends CsvResponse> clazz, HttpInputMessage inputMessage)
            throws IOException {
        //not used
        return null;
    }

    @Override
    protected void writeInternal(CsvResponse csvResponse, HttpOutputMessage outputMessage)
            throws IOException {
        outputMessage.getHeaders().setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));
        outputMessage.getHeaders().set("Content-Disposition", "attachment; filename=" + csvResponse.getFilename());
        outputMessage.getHeaders().set("Set-Cookie", "fileDownload=true;Path=/");
        OutputStream outputStream = outputMessage.getBody();
        IOUtils.write(csvResponse.getCsvData(), outputStream);
    }
}
