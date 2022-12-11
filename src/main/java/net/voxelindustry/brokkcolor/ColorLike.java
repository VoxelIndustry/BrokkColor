package net.voxelindustry.brokkcolor;

public interface ColorLike
{
    Color toColorFloat();

    Color32 toColor32();

    boolean isColorFloat();

    /**
     * @return whether this color is translucent or has any alpha component
     */
    boolean hasAlpha();

    /**
     * @return whether this color cannot be displayed due to zero opacity
     */
    boolean isInvisible();

    int toRGBInt();

    int toRGBAInt();

    String toHex();

    default boolean isColor32()
    {
        return !isColorFloat();
    }
}
