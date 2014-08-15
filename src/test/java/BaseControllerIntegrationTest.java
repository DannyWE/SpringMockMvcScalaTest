import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseControllerIntegrationTest {

    @Mock
    protected Validator mockValidator;

    @Before
    public void createValidator() {
        when(mockValidator.supports(any(Class.class))).thenReturn(true);
    }

    protected void verifyValidatorWasCalledForAParamOfType(Class<?> clazz) {
        verify(mockValidator).validate(isA(clazz), isA(Errors.class));
    }

    protected MockMvc setupMockControllers(Object... controllers) {
        return setupMockControllers(controllers, false);
    }

    protected MockMvc setupMockControllerWithInterceptors(Object controller) {
        return setupMockControllers(new Object[] {controller}, true);
    }

    protected MockMvc setupMockControllers(Object[] controllers, boolean withInterceptors) {
        StandaloneMockMvcBuilder builder = standaloneSetup(controllers)
                .setValidator(mockValidator)
                .setMessageConverters(getMessageConverterList());
        if (withInterceptors) {
            builder.addInterceptors(getInterceptors());
        }
        return builder.build();
    }

    protected HttpMessageConverter<?>[] getMessageConverterList() {
        List<HttpMessageConverter<?>> converterList = new ArrayList<>();

        // ControllerConfig is initiated only for getting ObjectMapper from it.
        ControllerConfig controllerConfig = new ControllerConfig();
        WebAppConfig webAppConfig = new WebAppConfig();
        ReflectionTestUtils.setField(webAppConfig, "controllerConfig", controllerConfig);
        webAppConfig.configureMessageConverters(converterList);
        return converterList.toArray(new HttpMessageConverter<?>[converterList.size()]);
    }

    private HandlerInterceptor[] getInterceptors() {
        final ArrayList<HandlerInterceptor> list = new ArrayList<>();

        WebAppConfig webAppConfig = new WebAppConfig();
        webAppConfig.addInterceptors(new InterceptorRegistry() {
            @Override
            public InterceptorRegistration addInterceptor(HandlerInterceptor interceptor) {
                list.add(interceptor);
                return super.addInterceptor(interceptor);
            }
        });
        return list.toArray(new HandlerInterceptor[list.size()]);
    }
}
