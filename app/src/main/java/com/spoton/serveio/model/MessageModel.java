package com.spoton.serveio.model;

public class MessageModel implements Comparable<MessageModel> {

    String sender,receiver,message,type;
    Long time;

    public MessageModel() {
    }

    public MessageModel(String sender, String receiver, Long time, String message,String type) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.message = message;
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(MessageModel messageModel) {
        Long t1 = Long.valueOf(this.time);
        Long t2 = Long.valueOf(messageModel.time);
        if(t1>t2){
            return 1;
        }else if(t1<t2){
            return -1;
        }else{
            return 0;
        }
    }
}
