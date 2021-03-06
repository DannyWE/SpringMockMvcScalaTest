import controller.ApplicationController;
import controller.Dispatcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ApplicationControllerIntegrationTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(new ApplicationController(mock(Dispatcher.class))).build();
    }

    @Test
    public void should() throws Exception {

        mockMvc.perform(post("/public/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }
}


