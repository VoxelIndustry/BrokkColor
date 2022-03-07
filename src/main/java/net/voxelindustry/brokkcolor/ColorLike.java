package net.voxelindustry.brokkcolor;

public interface ColorLike
{
    Color toColorFloat();

    Color32 toColor32();

    boolean isColorFloat();

    default boolean isColor32()
    {
        return !isColorFloat();
    }
}
