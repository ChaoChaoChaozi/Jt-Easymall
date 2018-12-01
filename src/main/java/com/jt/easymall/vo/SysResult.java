package com.jt.easymall.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * �����̳��Զ�����Ӧ�ṹ
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysResult implements Serializable{
	private static final long serialVersionUID = 1L;

	// ����jackson����
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // ��Ӧҵ��״̬
    /*
     * 200	�ɹ�
     * 201	����
     * 400	��������
     */
    private Integer status;

    // ��Ӧ��Ϣ
    private String msg;

    // ��Ӧ�е�����
    private Object data;

    public static SysResult build(Integer status, String msg, Object data) {
        return new SysResult(status, msg, data);
    }

    public static SysResult oK(Object data) {
        return new SysResult(data);
    }

    public static SysResult oK() {
        return new SysResult(null);
    }

    public SysResult() {

    }

    public static SysResult build(Integer status, String msg) {
        return new SysResult(status, msg, null);
    }

    public SysResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SysResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    
    public Boolean isOk() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * ��json�����ת��ΪSysResult����
     * 
     * @param jsonData json����
     * @param clazz SysResult�е�object����
     * @return
     */
    public static SysResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, SysResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * û��object�����ת��
     * 
     * @param json
     * @return
     */
    public static SysResult format(String json) {
        try {
            return MAPPER.readValue(json, SysResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object�Ǽ���ת��
     * 
     * @param jsonData json����
     * @param clazz �����е�����
     * @return
     */
    public static SysResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

}
