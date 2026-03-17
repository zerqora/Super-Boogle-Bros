package actions;


public enum MoveDirections 
{

    NEUTRAL(0),
    FORWARD(1),
    UP(2),
    BACKWARD(3),
    DOWN(4);

    
    private final int id;
    MoveDirections(int id)
    {
        this.id = id;
    }

    // iterate through every enum and return the match with the correct id
    public static MoveDirections getTypeFromId(int id){
        for(MoveDirections type : MoveDirections.values()){
            if(type.id == id){ return type;}
        }
        
        return null;
    }

    public int getId(){
        return id;
    }
}
