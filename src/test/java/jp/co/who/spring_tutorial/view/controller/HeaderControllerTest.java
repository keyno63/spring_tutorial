package jp.co.who.spring_tutorial.view.controller;

import jp.co.who.spring_tutorial.api.sample.controller.HeaderController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class HeaderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WebClient we;

    @Before
    public void setupMockMvc() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new HeaderController())
                .build();
    }

    @Test
    public void getCookieEndpoint() throws Exception {
        mockMvc.perform(get("/api/header/cookie")
                  .cookie(new Cookie("sample", "sample_value")))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserAgentEndpoint() throws Exception {
        mockMvc.perform(get("/api/header/userAgent").header("User-Agent", "sample_user_agent"))
                .andExpect(status().isOk());
        String x = "あああ";
        System.out.println(x);
    }
}