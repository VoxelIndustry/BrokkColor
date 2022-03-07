package net.voxelindustry.brokkcolor;

import static java.lang.Integer.max;
import static java.lang.Math.floor;
import static java.lang.Math.min;

public record Color32(byte red, byte green, byte blue, byte alpha) implements ColorLike
{
    public static final Color32 ALPHA = of(-128, -128, -128, -128);

    public Color32 interpolate(Color32 other, float delta)
    {
        if (delta >= 1)
            return other;
        if (delta <= 0)
            return this;

        return new Color32((byte) (red + (other.red - red) * delta),
                (byte) (green + (other.green - green) * delta),
                (byte) (blue + (other.blue - blue) * delta),
                (byte) (alpha + (other.alpha - alpha) * delta));
    }

    public Color32 addAlpha(int alpha)
    {
        return new Color32(red, green, blue, (byte) (this.alpha + alpha));
    }

    public static Color32 of(int red, int green, int blue, int alpha)
    {
        return new Color32((byte) red, (byte) green, (byte) blue, (byte) alpha);
    }

    public static Color32 fromColor128(Color color)
    {
        return new Color32(
                (byte) max(-128, min(127, (int) floor(color.getRed() * 256.0) - 128)),
                (byte) max(-128, min(127, (int) floor(color.getGreen() * 256.0) - 128)),
                (byte) max(-128, min(127, (int) floor(color.getBlue() * 256.0) - 128)),
                (byte) max(-128, min(127, (int) floor(color.getAlpha() * 256.0) - 128))
        );
    }

    @Override
    public Color toColorFloat()
    {
        return new Color(
                ((int) red) + 128 / 256F,
                ((int) green) + 128 / 256F,
                ((int) blue) + 128 / 256F,
                ((int) alpha) + 128 / 256F
        );
    }

    @Override
    public Color32 toColor32()
    {
        return this;
    }

    @Override
    public boolean isColorFloat()
    {
        return false;
    }
}
