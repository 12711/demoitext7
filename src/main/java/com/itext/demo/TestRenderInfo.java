package com.itext.demo;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.LineSegment;
import com.itextpdf.kernel.geom.Matrix;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.canvas.CanvasGraphicsState;
import com.itextpdf.kernel.pdf.canvas.CanvasTag;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;

import java.util.List;
import java.util.Stack;

/**
 * Created by lsm on 2018/2/5.
 */
public class TestRenderInfo extends TextRenderInfo{
    @Override
    public PdfString getPdfString() {
        return super.getPdfString();
    }

    public TestRenderInfo(PdfString str, CanvasGraphicsState gs, Matrix textMatrix, Stack<CanvasTag> canvasTagHierarchy) {
        super(str, gs, textMatrix, canvasTagHierarchy);
    }

    @Override
    public String getText() {
        return super.getText();
    }

    @Override
    public boolean hasMcid(int mcid) {
        return super.hasMcid(mcid);
    }

    @Override
    public boolean hasMcid(int mcid, boolean checkTheTopmostLevelOnly) {
        return super.hasMcid(mcid, checkTheTopmostLevelOnly);
    }

    @Override
    public int getMcid() {
        return super.getMcid();
    }

    @Override
    public LineSegment getBaseline() {
        return super.getBaseline();
    }

    @Override
    public LineSegment getUnscaledBaseline() {
        return super.getUnscaledBaseline();
    }

    @Override
    public LineSegment getAscentLine() {
        return super.getAscentLine();
    }

    @Override
    public LineSegment getDescentLine() {
        return super.getDescentLine();
    }

    @Override
    public PdfFont getFont() {
        return super.getFont();
    }

    @Override
    public float getRise() {
        return super.getRise();
    }

    @Override
    public List<TextRenderInfo> getCharacterRenderInfos() {
        return super.getCharacterRenderInfos();
    }

    @Override
    public float getSingleSpaceWidth() {
        return super.getSingleSpaceWidth();
    }

    @Override
    public int getTextRenderMode() {
        return super.getTextRenderMode();
    }

    @Override
    public Color getFillColor() {
        return super.getFillColor();
    }

    @Override
    public Color getStrokeColor() {
        return super.getStrokeColor();
    }

    @Override
    public float getFontSize() {
        return super.getFontSize();
    }

    @Override
    public float getHorizontalScaling() {
        return super.getHorizontalScaling();
    }

    @Override
    public float getCharSpacing() {
        return super.getCharSpacing();
    }

    @Override
    public float getWordSpacing() {
        return super.getWordSpacing();
    }

    @Override
    public float getLeading() {
        return super.getLeading();
    }

    @Override
    public String getActualText() {
        return super.getActualText();
    }

    @Override
    public String getExpansionText() {
        return super.getExpansionText();
    }

    @Override
    public boolean isReversedChars() {
        return super.isReversedChars();
    }

    @Override
    public List<CanvasTag> getCanvasTagHierarchy() {
        return super.getCanvasTagHierarchy();
    }

    @Override
    public float getUnscaledWidth() {
        return super.getUnscaledWidth();
    }

    @Override
    public boolean isGraphicsStatePreserved() {
        return super.isGraphicsStatePreserved();
    }

    @Override
    public void preserveGraphicsState() {
        super.preserveGraphicsState();
    }

    @Override
    public void releaseGraphicsState() {
        super.releaseGraphicsState();
    }
}
