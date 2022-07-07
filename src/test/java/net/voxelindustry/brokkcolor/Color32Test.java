package net.voxelindustry.brokkcolor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Color32Test
{
    @Test
    public void rgbTranslation()
    {
        assertThat(Color32.fromRGBInt(16711680).toColorFloat()).isEqualTo(Color.RED);
        assertThat(Color.RED.toColor32().toRGBInt()).isEqualTo(16711680);
    }

    @Test
    public void hexTranslation()
    {
        assertThat(Color32.fromHex("#00FFFF").toColorFloat()).isEqualTo(Color.AQUA);
        assertThat(Color.AQUA.toColor32().toHex()).isEqualToIgnoringCase("#00FFFF");
    }
}
