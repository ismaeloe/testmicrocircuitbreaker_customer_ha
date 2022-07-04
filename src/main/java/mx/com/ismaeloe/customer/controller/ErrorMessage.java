package mx.com.ismaeloe.customer.controller;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter @Builder
public class ErrorMessage {
	
    private String code ;
    private List<Map<String, String >> messages ;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Map<String, String>> getMessages() {
		return messages;
	}
	public void setMessages(List<Map<String, String>> messages) {
		this.messages = messages;
	}
    
    
}
