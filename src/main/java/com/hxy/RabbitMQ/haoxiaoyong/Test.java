package com.hxy.RabbitMQ.haoxiaoyong;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/12/5.
 */
public class Test {

    private static String KEY_WORD ="著名的教育";
    private static int i = 0;
    static List<float[]> arrays = new ArrayList<float[]>();
    static String sb ;

    public static List<float[]> getKeyWords(String filePath) {
        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            System.out.println("页数"+pageNum);
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);

            for (i = 1; i < 2; i++) {
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容

                        if (null != text && text.contains(KEY_WORD)) {
                            Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                            sb = boundingRectange.x+"--"+boundingRectange.y+"---";
                            //System.out.println("centerX:"+boundingRectange.getCenterX());
                           System.out.println("2D:"+boundingRectange.getBounds2D());
                            System.out.println("x:"+boundingRectange.getX());
                            System.out.println("y:"+boundingRectange.getY());
                            float[] resu = new float[3];
                            resu[0] = boundingRectange.x;
                            resu[1] = boundingRectange.y;
                            resu[2] = i;
                            arrays.add(resu);
                        }
                    }


                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void endTextBlock() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void beginTextBlock() {
                        // TODO Auto-generated method stub

                    }
                });
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrays;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        List<float[]> ff = getKeyWords("C:office/contract1511260888990.pdf");
        for(float[] f : ff){
            System.out.println(f[0]+"----"+f[1]+"-----"+f[2]);
        }
        System.out.println(sb);

    }
}
