package com.itext;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredTextEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import org.assertj.core.util.diff.Chunk;
import org.junit.Test;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 重新定义策略，覆盖读取字符
 */
public class Demo2 {

    @Test
    public void test2() throws Exception {
        String path = "C:\\Users\\lsm\\Desktop\\201801301724270339.pdf";
        LocationTextExtractionStrategy2 strategy2 = new LocationTextExtractionStrategy2();
        PdfReader pdfReader = new PdfReader(path);
        PdfDocument document = new PdfDocument(pdfReader);
        PdfPage page;
        PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy2);
        for(int i=1,length = document.getNumberOfPages();i<=length;i++){
            page = document.getPage(i);
            parser.processPageContent(page);
        }
        TextPlusY textPlusY = strategy2.getResultantTextPlusY();

        System.out.printf("\nText from test.pdf\n=====\n%s\n=====\n", textPlusY);
        System.out.print("\nText with y from test.pdf\n=====\n");
        int length = textPlusY.length();
        float lastY = Float.MIN_NORMAL;
        for (int i = 0; i < length; i++)
        {
            float y = (float)textPlusY.yCoordAt(i).get("Y");
            float x = (float)textPlusY.yCoordAt(i).get("X");
            if (y != lastY)
            {
                System.out.printf("\n(%4.1f) ", y);
                System.out.printf("\n(%4.1f) ", x);
                lastY = y;
            }
            System.out.print(textPlusY.charAt(i));
        }


        System.out.print("\nMatches of 'est' with y from test.pdf\n=====\n");
        Matcher matcher = Pattern.compile("功能申请进行调整。").matcher(textPlusY);
        while (matcher.find())
        {
            System.out.printf("from character %s to %s at y position (%4.1f) and x position (%4.1f) \n", matcher.start(), matcher.end(), textPlusY.yCoordAt(matcher.start()).get("Y"), textPlusY.yCoordAt(matcher.start()).get("X"));
        }
        System.out.print("\n=====\n");
    }

    @Test
    public void test3() throws Exception{


    }
}
