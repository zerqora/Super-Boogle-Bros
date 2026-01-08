


public class EventListener {
    

    public void received(Object p)
    {
        if(p instanceof AddPlayerPacket)
        {
            AddPlayerPacket packet = (AddPlayerPacket) p;
        }
    }
}
