package com.nearsoft.tbwlogistics.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebConfigTest {

    @Autowired
    private SpringWebConfig springWebConfig;

    @Autowired
    private MessageSource messageSource;

    @Test
    public void messageSourceIsDefinedInSpringWebConfig() {
        MessageSource messageSource = springWebConfig.messageSource();
        Assert.assertNotNull(messageSource);
    }

    @Test
    public void messageSourceIsLoadedInSpringContainer() {
        Assert.assertNotNull(messageSource);
    }
}
