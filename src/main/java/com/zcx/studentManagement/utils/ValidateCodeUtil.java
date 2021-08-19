package com.zcx.studentManagement.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateCodeUtil {

    private int width;
    private int height;
    private int num;
    private String code;
    private static final Random ran=new Random();
    /**
     * 单例模式获取对象方法
     */
    private static ValidateCodeUtil validateCodeUtil;
    private ValidateCodeUtil(){ //私有构造方法，单例模式
        code="01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        num=5;
    }
    public static ValidateCodeUtil getInstance(){   //只产生一个实例
        if(validateCodeUtil==null){
            validateCodeUtil=new ValidateCodeUtil();
        }
        return validateCodeUtil;
    }
    public void set(int width,int height,int num,String code){
        this.width=width;
        this.height=height;
        this.setNum(num);
        this.setCode(code);
    }

    public void set(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String generateCheckCode(){
        StringBuffer cc = new StringBuffer();
        for(int i=0;i<num;i++){
            cc.append(code.charAt(ran.nextInt(code.length())));
        }
        return cc.toString();
    }

    /**
     * 画画
     * @param checkcode 验证码
     * @return img
     */
    public BufferedImage generateCheckImg(String checkcode){
        //创建一个图片对象
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic =img.createGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0,0,width,height);
        graphic.setColor(Color.BLACK);
        graphic.drawRect(0,0,width-1,height-1);
        Font font =new Font("微软雅黑",Font.BOLD+Font.ITALIC,(int)(height*0.8));
        graphic.setFont(font);
        for(int i=0;i<num;i++){
            graphic.setColor(new Color(ran.nextInt(155),ran.nextInt(255),ran.nextInt(255)));
            graphic.drawString(String.valueOf(checkcode.charAt(i)),i*(width/num)+4,(int)(height*0.8));
        }
        //加一些点
        for(int i=0;i<(width+height);i++){
            graphic.setColor(new Color(ran.nextInt(155),ran.nextInt(255),ran.nextInt(255)));
            graphic.drawOval(ran.nextInt(width),ran.nextInt(height),1,1);
        }
        //加一些线
        for(int i=0;i<5;i++){
            graphic.setColor(new Color(ran.nextInt(155),ran.nextInt(255),ran.nextInt(255)));
            graphic.drawLine(0,ran.nextInt(height),width,ran.nextInt(height));
        }
        return img;
    }

}
