package net.voxelindustry.brokkcolor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Color1DGradientTest
{
    @Test
    public void twoValuesGradient()
    {
        Color1DGradient gradient = Color1DGradient.build().color(Color.RED, 0).color(Color.YELLOW, 1).create();

        assertThat(gradient.getValue(0)).isEqualTo(Color.RED);
        assertThat(gradient.getValue(1)).isEqualTo(Color.YELLOW);
    }

    @Test
    public void twoValuesCachedGradient()
    {
        Color1DGradient gradient = Color1DGradient.build().color(Color.RED, 0).color(Color.YELLOW, 1).precompute(0.5F).create();

        assertThat(gradient.getValue(0)).isEqualTo(Color.RED);
        assertThat(gradient.getValue(1)).isEqualTo(Color.YELLOW);
    }

    @Test
    public void threeValuesWrappingGradient()
    {
        Color1DGradient gradient = Color1DGradient.build().color(Color.RED, 0).color(Color.YELLOW, 0.5F).color(Color.RED, 1).create();

        assertThat(gradient.getValue(0)).isEqualTo(Color.RED);
        assertThat(gradient.getValue(0.5F)).isEqualTo(Color.YELLOW);
        assertThat(gradient.getValue(1)).isEqualTo(Color.RED);

        assertThat(gradient.getValue(0.25F)).isEqualTo(Color.RED.interpolate(Color.YELLOW, 0.5F, new Color()));
        assertThat(gradient.getValue(0.75F)).isEqualTo(Color.RED.interpolate(Color.YELLOW, 0.5F, new Color()));
    }

    @Test
    public void threeValuesWrappingCachedGradient()
    {
        Color1DGradient gradient = Color1DGradient.build().color(Color.RED, 0).color(Color.YELLOW, 0.5F).color(Color.RED, 1).precompute(0.05F).create();

        assertThat(gradient.getValue(0)).isEqualTo(Color.RED);
        assertThat(gradient.getValue(0.5F)).isEqualTo(Color.YELLOW);
        assertThat(gradient.getValue(1)).isEqualTo(Color.RED);

        assertThat(gradient.getValue(0.25F)).isEqualTo(Color.RED.interpolate(Color.YELLOW, 0.5F, new Color()));
        assertThat(gradient.getValue(0.75F)).isEqualTo(Color.RED.interpolate(Color.YELLOW, 0.5F, new Color()));
    }
}
