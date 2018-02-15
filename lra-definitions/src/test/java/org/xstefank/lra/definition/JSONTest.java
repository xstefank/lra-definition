package org.xstefank.lra.definition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.xstefank.lra.model.ActionResult;

import java.io.IOException;

public class JSONTest {

    @Test
    public void testSaveJSON() throws IOException {
        LRADefinition lra = LRABuilder.lra()
                .name("testLRA")
                .withAction(d -> ActionResult.SUCCESS)
                .data(42)
                .build();

        final String expected = "{\"name\":\"testLRA\",\"actions\":[{}],\"data\":42,\"nestedLRAs\":[]}";

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(expected, mapper.writeValueAsString(lra));
    }

    @Test
    public void testJSONWithNestedLRA() throws JsonProcessingException {
        LRADefinition lra = LRABuilder.lra()
                .name("topLRA")
                .withAction(d -> ActionResult.SUCCESS)
                .nested()
                    .name("nestedLRA")
                    .withAction(d -> ActionResult.SUCCESS)
                    .data(41)
                    .end()
                .data(42)
                .build();

        final String expected = "{\"name\":\"topLRA\",\"actions\":[{}],\"data\":42,\"nestedLRAs\":[{\"name\":\"nestedLRA\",\"actions\":[{}],\"data\":41,\"nestedLRAs\":[]}]}";

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(expected, mapper.writeValueAsString(lra));
    }
}
