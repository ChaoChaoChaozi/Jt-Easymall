package com.jt.easymall.vo;

public class PicUploadResult {
	private Integer error=0;		//ͼƬ�ϴ��������׳����׳����޷�����jspҳ��ص����������������ʶ��0��ʾ���쳣��1�����쳣
    private String url;             //Ӧ����������ܹ������ľ���ҳ��·��  ���·��http://image.jt/1212.jpg 
    								//����·��:���ͼƬ·��     D:\jt-upload\images\2017\06\03
    private String width;
    private String height;          //�����Ϊ�Ĳ�ָ��,��ʹ�õ���ͼƬ�Լ��ĳߴ�

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    

}
