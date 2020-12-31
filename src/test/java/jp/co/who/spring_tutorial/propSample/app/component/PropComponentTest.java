package jp.co.who.spring_tutorial.propSample.app.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PropComponentTest {

    @Autowired
    PropComponent propComponent;

    @Test
    public void urlTest() {
        var actual = propComponent.getUrl();
        assertThat(actual).isEqualTo("https://xxxx.com");
    }

    @Test
    public void timeoutTest() {
        var actual = propComponent.getTimeout();
        assertThat(actual).isEqualTo(1000);
    }
}
