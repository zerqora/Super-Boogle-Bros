package packets;

import java.io.Serializable;


public class NewChatPacket implements Serializable
{
    private static final long serialVersionUID = 1L;

    public int id;
    public String name;
    public String message;

}