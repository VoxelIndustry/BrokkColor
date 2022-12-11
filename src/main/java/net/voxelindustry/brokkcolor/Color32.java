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

    public static Color32 fromHex(String hex)
    {
        return fromHex(hex, 255);
    }

    /**
     * @param hex   string like #ABCD01
     * @param alpha numeric component of range [0 ; 256[
     * @return new Color32 instance from parsed hex
     */
    public static Color32 fromHex(String hex, int alpha)
    {
        var padding = hex.startsWith("#") ? 1 : 0;
        var red = Integer.parseInt(hex.substring(padding, 2 + padding), 16);
        var green = Integer.parseInt(hex.substring(2 + padding, 4 + padding), 16);
        var blue = Integer.parseInt(hex.substring(4 + padding, 6 + padding), 16);
        return Color32.of(red - 128, green - 128, blue - 128, alpha - 128);
    }

    public static Color32 fromRGBInt(int rgb)
    {
        return Color32.of((rgb >> 16 & 0xFF) - 128, (rgb >> 8 & 0xFF) - 128, (rgb & 0xFF) - 128, 127);
    }

    public static Color32 fromARGBInt(int rgba)
    {
        return Color32.of((rgba >> 24 & 0xFF) - 128,
                (rgba >> 16 & 0xFF) - 128,
                (rgba >> 8 & 0xFF) - 128,
                (rgba & 0xFF) - 128);
    }

    public static Color32 fromRGBAInt(int argb)
    {
        return Color32.of(
                (argb >> 16 & 0xFF) - 128,
                (argb >> 8 & 0xFF) - 128,
                (argb & 0xFF) - 128,
                (argb >> 24 & 0xFF) - 128);
    }

    @Override
    public Color toColorFloat()
    {
        return new Color(
                (((int) red) + 128) / 255F,
                (((int) green) + 128) / 255F,
                (((int) blue) + 128) / 255F,
                (((int) alpha) + 128) / 255F
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

    @Override
    public boolean hasAlpha()
    {
        return alpha < 127;
    }

    @Override
    public boolean isInvisible()
    {
        return alpha == -128;
    }

    @Override
    public int toRGBInt()
    {
        int rtn = 0;
        rtn |= (red + 128) << 16;
        rtn |= (green + 128) << 8;
        rtn |= blue + 128;

        return rtn;
    }

    @Override
    public int toRGBAInt()
    {
        int rtn = 0;
        rtn |= (alpha + 128) << 24;
        rtn |= (red + 128) << 16;
        rtn |= (green + 128) << 8;
        rtn |= blue + 128;

        return rtn;
    }

    @Override
    public String toHex()
    {
        return "#" + String.format("%02X", red + 128) +
                String.format("%02X", green + 128) +
                String.format("%02X", blue + 128);
    }
}
