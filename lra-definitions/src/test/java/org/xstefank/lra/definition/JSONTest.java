package org.xstefank.lra.definition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.xstefank.lra.definition.TestUtil.dummyAction;

@SuppressWarnings(value = "unchecked")
public class JSONTest {

    @Test
    public void testSimpleJSON() throws IOException {
        LRADefinition lra = LRABuilder.lra()
                .name("testLRA")
                .withAction(dummyAction())
                .data(42)
                .build();

        final String expected = "{" +
                "\"name\":\"testLRA\"," +
                "\"actions\":[{}]," +
                "\"data\":42," +
                "\"parentLRA\":null," +
                "\"clientId\":\"\"," +
                "\"timelimit\":0," +
                "\"nestedLRAs\":[]" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(expected, mapper.writeValueAsString(lra));
    }

    @Test
    public void testJSONWithNestedLRA() throws JsonProcessingException {
        LRADefinition lra = LRABuilder.lra()
                .name("topLRA")
                .withAction(dummyAction())
                .nested()
                    .name("nestedLRA")
                    .withAction(dummyAction())
                    .data(41)
                    .end()
                .data(42)
                .build();

        final String expected = "{" +
                "\"name\":\"topLRA\"," +
                "\"actions\":[{}]," +
                "\"data\":42," +
                "\"parentLRA\":null," +
                "\"clientId\":\"\"," +
                "\"timelimit\":0," +
                "\"nestedLRAs\":[{" +
                    "\"name\":\"nestedLRA\"," +
                    "\"actions\":[{}]," +
                    "\"data\":41,\"" +
                    "parentLRA\":null," +
                    "\"clientId\":\"\"," +
                    "\"timelimit\":0," +
                    "\"nestedLRAs\":[]}]" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(expected, mapper.writeValueAsString(lra));
    }

    @Test
    public void testJSONComplex() throws Exception {
        LRADefinition lra = LRABuilder.lra()
                .name("testLRA")
                .withAction(dummyAction())
                .data(42)
                .parentLRA(new URL("http://parent.lra"))
                .clientId("clientId")
                .timelimit(30, TimeUnit.SECONDS)
                .build();


        String expected = "{" +
                "\"name\":\"testLRA\"," +
                "\"actions\":[{}]," +
                "\"data\":42," +
                "\"parentLRA\":\"http://parent.lra\"," +
                "\"clientId\":\"clientId\"," +
                "\"timelimit\":30000," +
                "\"nestedLRAs\":[]" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(expected, mapper.writeValueAsString(lra));
    }

}
