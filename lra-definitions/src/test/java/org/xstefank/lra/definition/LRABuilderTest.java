package org.xstefank.lra.definition;

import org.junit.Assert;
import org.junit.Test;
import org.xstefank.lra.model.ActionResult;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class LRABuilderTest {

    @Test
    public void testSimpleLRA() {
        LRADefinition lra = LRABuilder.lra()
                .name("test")
                .withAction(d -> ActionResult.SUCCESS)
                .build();

        Assert.assertEquals("test", lra.getName());
        Assert.assertEquals(1, lra.getActions().size());
    }

    @Test
    public void testComplexLRA() {
        LRADefinition lra = LRABuilder.lra()
                .name("test")
                .withAction(d -> ActionResult.SUCCESS)
                .withAction(d -> ActionResult.SUCCESS)
                .data(42)
                .build();

        Assert.assertEquals("test", lra.getName());
        Assert.assertEquals(2, lra.getActions().size());
        Assert.assertEquals(42, lra.getData());
    }

    @Test
    public void testNestedLRA() {
        LRADefinition lra = LRABuilder.lra()
                .name("topLRA")
                .withAction(d -> ActionResult.SUCCESS)
                .nested()
                    .name("nestedLRA")
                    .withAction(d -> ActionResult.SUCCESS)
                    .end()
                .data(42)
                .build();

        Assert.assertEquals("topLRA", lra.getName());
        Assert.assertEquals(1, lra.getActions().size());
        Assert.assertEquals(42, lra.getData());

        List<LRADefinition> nestedLRAs = lra.getNestedLRAs();
        Assert.assertNotNull(nestedLRAs);
        Assert.assertEquals(1, lra.getNestedLRAs().size());
        Assert.assertEquals("nestedLRA", nestedLRAs.get(0).getName());
        Assert.assertEquals(1, nestedLRAs.get(0).getActions().size());
    }

}
