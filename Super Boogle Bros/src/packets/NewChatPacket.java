package packets;

import java.io.Serializable;


public class NewChatPacket implements Serializable
{
    private static final long serialVersionUID = 1L;

    public NewChatPacket(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
    public int id;
    public String name;
    public String message;

}