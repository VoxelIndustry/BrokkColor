package net.voxelindustry.brokkcolor;

import java.util.Objects;

import static java.lang.Integer.max;
import static java.lang.Math.floor;
import static java.lang.Math.min;

public class Color32
{
    public static final Color32 ALPHA = of(-128, -128, -128, -128);

    private final byte red;
    private final byte green;
    private final byte blue;
    private final byte alpha;

    public Color32(byte red, byte green, byte blue, byte alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

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

    public byte red()
    {
        return red;
    }

    public byte green()
    {
        return green;
    }

    public byte blue()
    {
        return blue;
    }

    public byte alpha()
    {
        return alpha;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color32 color32 = (Color32) o;
        return red == color32.red && green == color32.green && blue == color32.blue && alpha == color32.alpha;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(red, green, blue, alpha);
    }

    @Override
    public String toString()
    {
        return "Color32{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }
}
