package net.voxelindustry.brokkcolor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorTest
{
    @Test
    public void rgbTranslation()
    {
        assertThat(Color.fromRGBInt(16711680)).isEqualTo(Color.RED);
        assertThat(Color.RED.toRGBInt()).isEqualTo(16711680);
    }

    @Test
    public void hexTranslation()
    {
        assertThat(Color.fromHex("#00FFFF")).isEqualTo(Color.AQUA);
        assertThat(Color.AQUA.toHex()).isEqualToIgnoringCase("#00FFFF");
    }

    @Test
    public void copy()
    {
        assertThat(Color.RED.copy()).isEqualTo(Color.RED);
    }

    @Test
    public void modifiers()
    {
        assertThat(Color.RED.addGreen(0.1F, new Color()).getGreen()).isEqualTo(0.1F);
        assertThat(Color.BLUE.addRed(0.1F, new Color()).getRed()).isEqualTo(0.1F);
        assertThat(Color.RED.addBlue(0.1F, new Color()).getBlue()).isEqualTo(0.1F);
        assertThat(Color.BLACK.addAlpha(-0.1F, new Color()).getAlpha()).isEqualTo(0.9F);

        assertThat(Color.WHITE.shade(0.1F, new Color())).isEqualTo(new Color(0.9F, 0.9F, 0.9F));
    }
}
