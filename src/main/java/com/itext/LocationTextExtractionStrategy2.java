package com.itext;


import com.itextpdf.kernel.geom.Vector;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsm on 2018/1/26.
 */
public class LocationTextExtractionStrategy2 extends LocationTextExtractionStrategy {
    static Field locationalResultField;
    static Method sortWithMarksMethod;
    static Method startsWithSpaceMethod;
    static Method endsWithSpaceMethod;

    static Method textChunkSameLineMethod;

    static
    {
        try
        {
            locationalResultField = LocationTextExtractionStrategy.class.getDeclaredField("locationalResult");
            locationalResultField.setAccessible(true);
            sortWithMarksMethod = LocationTextExtractionStrategy.class.getDeclaredMethod("sortWithMarks", List.class);
            sortWithMarksMethod.setAccessible(true);
            startsWithSpaceMethod = LocationTextExtractionStrategy.class.getDeclaredMethod("startsWithSpace", String.class);
            startsWithSpaceMethod.setAccessible(true);
            endsWithSpaceMethod = LocationTextExtractionStrategy.class.getDeclaredMethod("endsWithSpace", String.class);
            endsWithSpaceMethod.setAccessible(true);
            textChunkSameLineMethod = TextChunk.class.getDeclaredMethod("sameLine", TextChunk.class);
            textChunkSameLineMethod.setAccessible(true);
        }
        catch(NoSuchFieldException | NoSuchMethodException | SecurityException e)
        {
            // Reflection failed
            e.printStackTrace();
        }
    }

    //
    // constructors
    //
    public LocationTextExtractionStrategy2()
    {
        super();
    }

    @Override
    public String getResultantText()
    {
        try {
            return getResultantTextPlusY().toString();
        }catch (Exception e){
            return "";
        }

    }

    @Override
    public void eventOccurred(IEventData data, EventType type) {
        super.eventOccurred(data, type);
    }

    public TextPlusY getResultantTextPlusY() throws Exception
    {
        try
        {
            List<TextChunk> textChunks = new ArrayList<>((List<TextChunk>)locationalResultField.get(this));
            sortWithMarksMethod.invoke(this, textChunks);

            TextPlusY textPlusY = new TextPlusY();
            TextChunk lastChunk = null;
            for (TextChunk chunk : textChunks)
            {
                float chunkY = chunk.getLocation().getStartLocation().get(Vector.I2);
                float chunkX = chunk.getLocation().getStartLocation().get(Vector.I1);
                float chunkX1 = chunk.getLocation().getEndLocation().get(Vector.I1);

                if (lastChunk == null)
                {
                    textPlusY.add(chunk.getText(), chunkY, chunkX);
                }
                else if ((Boolean)textChunkSameLineMethod.invoke(chunk, lastChunk))
                {
                    // we only insert a blank space if the trailing character of the previous string wasn't a space, and the leading character of the current string isn't a space
                    if (isChunkAtWordBoundary(chunk, lastChunk) &&
                            !(Boolean)startsWithSpaceMethod.invoke(this, chunk.getText()) &&
                            !(Boolean)endsWithSpaceMethod.invoke(this, lastChunk.getText()))
                    {
                        textPlusY.add(" ", chunkY,chunkX);
                    }

                    textPlusY.add(chunk.getText(), chunkY,chunkX);
                }
                else
                {
                    textPlusY.add("\n", lastChunk.getLocation().getStartLocation().get(Vector.I2),chunkX);
                    textPlusY.add(chunk.getText(), chunkY,chunkX);
                }
                lastChunk = chunk;
            }

            return textPlusY;
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new RuntimeException("Reflection failed", e);
        }
    }
}
