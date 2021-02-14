package net.voxelindustry.brokkcolor;

public class Color
{
    public static final Color ALPHA      = new Color(0, 0, 0, 0);
    public static final Color BLACK      = Color.fromHex("000000");
    public static final Color AQUA       = Color.fromHex("00FFFF");
    public static final Color WHITE      = Color.fromHex("FFFFFF");
    public static final Color RED        = Color.fromHex("FF0000");
    public static final Color LIGHT_GRAY = Color.fromHex("D3D3D3");
    public static final Color BLUE       = Color.fromHex("0000FF");
    public static final Color GRAY       = Color.fromHex("808080");
    public static final Color GREEN      = Color.fromHex("008000");
    public static final Color YELLOW     = Color.fromHex("FFFF00");
    public static final Color LIME       = Color.fromHex("00FF00");

    public static Color fromHex(String hex)
    {
        return Color.fromHex(hex, 1);
    }

    public static Color fromHex(String hex, final float alpha)
    {
        final Color rtn = new Color(0, 0, 0);

        final int padding = hex.startsWith("#") ? 1 : 0;
        rtn.red = Integer.parseInt(hex.substring(padding, 2 + padding), 16) / 255.0F;
        rtn.green = Integer.parseInt(hex.substring(2 + padding, 4 + padding), 16) / 255.0F;
        rtn.blue = Integer.parseInt(hex.substring(4 + padding, 6 + padding), 16) / 255.0F;
        rtn.alpha = alpha;
        return rtn;
    }

    public String toHex()
    {
        return "#" + String.format("%02X", (int) (this.red * 255)) +
                String.format("%02X", (int) (this.green * 255)) +
                String.format("%02X", (int) (this.blue * 255));
    }

    public static Color fromRGBInt(int rgb)
    {
        return new Color((rgb >> 16 & 0xFF) / 255f, (rgb >> 8 & 0xFF) / 255f, (rgb & 0xFF) / 255f);
    }

    public static Color fromRGBAInt(int rgba)
    {
        return new Color((rgba >> 24 & 0xFF) / 255f, (rgba >> 16 & 0xFF) / 255f, (rgba >> 8 & 0xFF) / 255f, (rgba &
                0xFF) / 255f);
    }

    public int toRGBInt()
    {
        int rtn = 0;
        rtn |= (int) (this.getRed() * 255) << 16;
        rtn |= (int) (this.getGreen() * 255) << 8;
        rtn |= (int) (this.getBlue() * 255);

        return rtn;
    }

    public int toRGBAInt()
    {
        int rtn = 0;
        rtn |= (int) (this.getAlpha() * 255) << 24;
        rtn |= (int) (this.getRed() * 255) << 16;
        rtn |= (int) (this.getGreen() * 255) << 8;
        rtn |= (int) (this.getBlue() * 255);

        return rtn;
    }

    public static Color from(final Color c)
    {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    private float red, blue, green, alpha;

    public Color(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(float red, float green, float blue)
    {
        this(red, green, blue, 1);
    }

    public Color addAlpha(float alpha)
    {
        final Color rtn = Color.from(this);
        rtn.setAlpha(alpha + rtn.getAlpha());
        return rtn;
    }

    public Color addGreen(float green)
    {
        final Color rtn = Color.from(this);
        rtn.setGreen(green + rtn.getGreen());
        return rtn;
    }

    public Color addBlue(float blue)
    {
        final Color rtn = Color.from(this);
        rtn.setBlue(blue + rtn.getBlue());
        return rtn;
    }

    public Color addRed(float red)
    {
        final Color rtn = Color.from(this);
        rtn.setRed(red + rtn.getRed());
        return rtn;
    }

    public Color shade(float shading)
    {
        final Color rtn = Color.from(this);
        rtn.setRed(rtn.getRed() - shading);
        rtn.setGreen(rtn.getGreen() - shading);
        rtn.setBlue(rtn.getBlue() - shading);
        return rtn;
    }

    public Color interpolate(Color other, float delta)
    {
        if (delta >= 1)
            return other;
        if (delta <= 0)
            return this;

        return new Color(red + (other.red - red) * delta,
                green + (other.green - green) * delta,
                blue + (other.blue - blue) * delta,
                alpha + (other.alpha - alpha) * delta);
    }

    public float getRed()
    {
        return this.red;
    }

    public float getBlue()
    {
        return this.blue;
    }

    public float getGreen()
    {
        return this.green;
    }

    public float getAlpha()
    {
        return this.alpha;
    }

    public void setRed(final float red)
    {
        this.red = red;
    }

    public void setBlue(final float blue)
    {
        this.blue = blue;
    }

    public void setGreen(final float green)
    {
        this.green = green;
    }

    public void setAlpha(final float alpha)
    {
        this.alpha = alpha;
    }

    public Color32 toColor32()
    {
        return Color32.fromColor128(this);
    }

    @Override
    public String toString()
    {
        return "Color [red=" + this.red + ", blue=" + this.blue + ", green=" + this.green + ", alpha=" + this.alpha
                + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(this.alpha);
        result = prime * result + Float.floatToIntBits(this.blue);
        result = prime * result + Float.floatToIntBits(this.green);
        result = prime * result + Float.floatToIntBits(this.red);
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        final Color other = (Color) obj;
        if (Float.floatToIntBits(this.alpha) != Float.floatToIntBits(other.alpha))
            return false;
        if (Float.floatToIntBits(this.blue) != Float.floatToIntBits(other.blue))
            return false;
        if (Float.floatToIntBits(this.green) != Float.floatToIntBits(other.green))
            return false;
        return Float.floatToIntBits(this.red) == Float.floatToIntBits(other.red);
    }
}