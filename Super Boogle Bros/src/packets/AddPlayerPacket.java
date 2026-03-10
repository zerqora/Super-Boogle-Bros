package packets;

import java.io.Serializable;


public class AddPlayerPacket implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String name;
    public int id;

    public AddPlayerPacket(int id, String name){
        this.name = name;
        this.id = id;
    }
}