package com.itext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsm on 2018/1/26.
 */
public class TextPlusY implements CharSequence
{
    final List<String> texts = new ArrayList<>();
    final List<Float> yCoords = new ArrayList<>();
    final List<Float> xCoords = new ArrayList<>();

    //
    // CharSequence implementation
    //
    @Override
    public int length()
    {
        int length = 0;
        for (String text : texts)
        {
            length += text.length();
        }
        return length;
    }

    @Override
    public char charAt(int index)
    {
        for (String text : texts)
        {
            if (index < text.length())
            {
                return text.charAt(index);
            }
            index -= text.length();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public CharSequence subSequence(int start, int end)
    {
        TextPlusY result = new TextPlusY();
        int length = end - start;
        for (int i = 0; i < yCoords.size(); i++)
        {
            String text = texts.get(i);
            if (start < text.length())
            {
                float yCoord = yCoords.get(i);
                float xCoord = xCoords.get(i);

                if (start > 0)
                {
                    text = text.substring(start);
                    start = 0;
                }
                if (length > text.length())
                {
                    result.add(text, yCoord,xCoord);
                }
                else
                {
                    result.add(text.substring(0, length), yCoord,xCoord);
                    break;
                }
            }
            else
            {
                start -= text.length();
            }
        }
        return result;
    }

    //
    // Object overrides
    //
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (String text : texts)
        {
            builder.append(text);
        }
        return builder.toString();
    }

    //
    // y coordinate support
    //
    public TextPlusY add(String text, float y, float x)
    {
        if (text != null)
        {
            texts.add(text);
            yCoords.add(y);
            xCoords.add(x);
        }
        return this;
    }

    public Map yCoordAt(int index)
    {
        Map position = new HashMap();
        for (int i = 0; i < yCoords.size(); i++)
        {
            String text = texts.get(i);
            if (index < text.length())
            {
                position.put("X",xCoords.get(i));
                position.put("Y",yCoords.get(i));
                return position;
            }
            index -= text.length();
        }
        throw new IndexOutOfBoundsException();
    }
}