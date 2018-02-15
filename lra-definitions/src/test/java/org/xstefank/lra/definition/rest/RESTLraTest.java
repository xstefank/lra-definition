package org.xstefank.lra.definition.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xstefank.lra.model.ActionResult;

public class RESTLraTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testJsonWithCallback() throws JsonProcessingException {
        RESTLra lra = RESTLraBuilder.lra()
                .name("testLRA")
                .withAction(d -> ActionResult.SUCCESS)
                .data(42)
                .callback("http://testLocal.org")
                .build();

        String expected = "{\"name\":\"testLRA\",\"actions\":[{}],\"data\":42,\"callbackURL\":\"http://testLocal.org\",\"nestedLRAs\":[]}";

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(expected, mapper.writeValueAsString(lra));
    }

    @Test
    public void testJsonWithNullCallback() {
        expectedException.expect(IllegalArgumentException.class);

        RESTLra lra = RESTLraBuilder.lra()
                .name("testLRA")
                .withAction(d -> ActionResult.SUCCESS)
                .build();
    }
}
