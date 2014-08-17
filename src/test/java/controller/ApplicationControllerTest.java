package controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ApplicationControllerTest {


    private MockMvc mockMvc;
    private Dispatcher mockDispathcer;

    @Before
    public void setup() {
        mockDispathcer = mock(Dispatcher.class);
        mockMvc = standaloneSetup(new ApplicationController(mockDispathcer)).build();
    }

    @Test
    public void should() throws Exception {




        ArgumentCaptor<Query> captor = ArgumentCaptor.forClass(Query.class);

        mockMvc.perform(post("/public/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        verify(mockDispathcer).dispatch(captor.capture());

        Query value = captor.getValue();

        System.out.println(value);

    }
}
