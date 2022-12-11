package net.voxelindustry.brokkcolor;

public class Color implements ColorLike
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

    public static Color fromHex(String hex, float alpha)
    {
        var result = new Color(0, 0, 0);

        var padding = hex.startsWith("#") ? 1 : 0;
        result.red = Integer.parseInt(hex.substring(padding, 2 + padding), 16) / 255.0F;
        result.green = Integer.parseInt(hex.substring(2 + padding, 4 + padding), 16) / 255.0F;
        result.blue = Integer.parseInt(hex.substring(4 + padding, 6 + padding), 16) / 255.0F;
        result.alpha = alpha;
        return result;
    }

    @Override
    public String toHex()
    {
        return "#" + String.format("%02X", (int) (red * 255)) +
                String.format("%02X", (int) (green * 255)) +
                String.format("%02X", (int) (blue * 255));
    }

    public static Color fromRGBInt(int rgb)
    {
        return new Color((rgb >> 16 & 0xFF) / 255f, (rgb >> 8 & 0xFF) / 255f, (rgb & 0xFF) / 255f);
    }

    public static Color fromARGBInt(int rgba)
    {
        return new Color((rgba >> 24 & 0xFF) / 255f,
                (rgba >> 16 & 0xFF) / 255f,
                (rgba >> 8 & 0xFF) / 255f,
                (rgba & 0xFF) / 255f);
    }

    public static Color fromRGBAInt(int argb)
    {
        return new Color(
                (argb >> 16 & 0xFF) / 255f,
                (argb >> 8 & 0xFF) / 255f,
                (argb & 0xFF) / 255f,
                (argb >> 24 & 0xFF) / 255f);
    }

    @Override
    public int toRGBInt()
    {
        int rtn = 0;
        rtn |= (int) (getRed() * 255) << 16;
        rtn |= (int) (getGreen() * 255) << 8;
        rtn |= (int) (getBlue() * 255);

        return rtn;
    }

    @Override
    public int toRGBAInt()
    {
        int rtn = 0;
        rtn |= (int) (getAlpha() * 255) << 24;
        rtn |= (int) (getRed() * 255) << 16;
        rtn |= (int) (getGreen() * 255) << 8;
        rtn |= (int) (getBlue() * 255);

        return rtn;
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

    public Color(Color other)
    {
        this(other.getRed(), other.getGreen(), other.getBlue(), other.getAlpha());
    }

    public Color()
    {
        this(0, 0, 0);
    }

    public Color set(Color other)
    {
        red = other.red;
        green = other.green;
        blue = other.blue;
        alpha = other.alpha;
        return this;
    }

    public Color copy()
    {
        return new Color(this);
    }

    public Color addAlpha(float alpha)
    {
        return addAlpha(alpha, this);
    }

    public Color addAlpha(float alpha, Color dest)
    {
        dest.set(this);
        dest.alpha += alpha;

        return dest;
    }

    public Color addGreen(float green)
    {
        return addGreen(green, this);
    }

    public Color addGreen(float green, Color dest)
    {
        dest.set(this);
        dest.green += green;

        return dest;
    }

    public Color addBlue(float blue)
    {
        return addBlue(blue, this);
    }

    public Color addBlue(float blue, Color dest)
    {
        dest.set(this);
        dest.blue += blue;

        return dest;
    }

    public Color addRed(float red)
    {
        return addRed(red, this);
    }

    public Color addRed(float red, Color dest)
    {
        dest.set(this);
        dest.red += red;

        return dest;
    }

    public Color shade(float shading)
    {
        return shade(shading, this);
    }

    public Color shade(float shading, Color dest)
    {
        dest.setRed(getRed() - shading);
        dest.setGreen(getGreen() - shading);
        dest.setBlue(getBlue() - shading);
        dest.setAlpha(getAlpha());

        return dest;
    }

    public Color interpolate(Color other, float delta)
    {
        return interpolate(other, delta, this);
    }

    public Color interpolate(Color other, float delta, Color dest)
    {
        if (delta >= 1)
            return dest.set(other);
        if (delta <= 0)
            return dest.set(this);

        dest.red = red + (other.red - red) * delta;
        dest.green = green + (other.green - green) * delta;
        dest.blue = blue + (other.blue - blue) * delta;
        dest.alpha = alpha + (other.alpha - alpha) * delta;

        return dest;
    }

    public float getRed()
    {
        return red;
    }

    public float getBlue()
    {
        return blue;
    }

    public float getGreen()
    {
        return green;
    }

    public float getAlpha()
    {
        return alpha;
    }

    public void setRed(float red)
    {
        setRed(red, this);
    }

    public void setBlue(float blue)
    {
        setBlue(blue, this);
    }

    public void setGreen(float green)
    {
        setGreen(green, this);
    }

    public void setAlpha(float alpha)
    {
        setAlpha(alpha, this);
    }

    public void setRed(float red, Color dest)
    {
        dest.set(this);
        dest.red = red;
    }

    public void setBlue(float blue, Color dest)
    {
        dest.set(this);
        dest.blue = blue;
    }

    public void setGreen(float green, Color dest)
    {
        dest.set(this);
        dest.green = green;
    }

    public void setAlpha(float alpha, Color dest)
    {
        dest.set(this);
        dest.alpha = alpha;
    }

    public void set(int index, float value)
    {
        set(index, value, this);
    }

    public void set(int index, float value, Color dest)
    {
        dest.set(this);

        switch (index)
        {
            case 0 -> dest.red = value;
            case 1 -> dest.green = value;
            case 2 -> dest.blue = value;
            case 3 -> dest.alpha = value;
            default ->
                    throw new IllegalArgumentException("ColorFloat is a 4 elements RGBA color representation. Element at index " + index + " is unknown");
        }
    }

    @Override
    public Color toColorFloat()
    {
        return this;
    }

    @Override
    public Color32 toColor32()
    {
        return Color32.fromColor128(this);
    }

    @Override
    public boolean isColorFloat()
    {
        return true;
    }

    @Override
    public boolean hasAlpha()
    {
        return alpha < 1;
    }

    @Override
    public boolean isInvisible()
    {
        return alpha == 0;
    }

    @Override
    public String toString()
    {
        return "Color [red=" + red + ", blue=" + blue + ", green=" + green + ", alpha=" + alpha + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(alpha);
        result = prime * result + Float.floatToIntBits(blue);
        result = prime * result + Float.floatToIntBits(green);
        result = prime * result + Float.floatToIntBits(red);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Color other = (Color) obj;
        if (Float.floatToIntBits(alpha) != Float.floatToIntBits(other.alpha))
            return false;
        if (Float.floatToIntBits(blue) != Float.floatToIntBits(other.blue))
            return false;
        if (Float.floatToIntBits(green) != Float.floatToIntBits(other.green))
            return false;
        return Float.floatToIntBits(red) == Float.floatToIntBits(other.red);
    }
}