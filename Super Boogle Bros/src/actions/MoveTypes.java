package actions;

public enum MoveTypes 
{
    
    IDLE(0),
    WALK(1),
    RUN(2),
    JUMP(3),
    AIR_IDLE(4),
    BASIC_N(5),
    BASIC_H(6),
    AIR_N(7);
     // add more i dont remember



    private final int id;
    MoveTypes(int id){
        this.id = id;
    }

    // iterate through every enum and return the match with the correct id
    public static MoveTypes getTypeFromId(int id){
        for(MoveTypes type : MoveTypes.values()){
            if(type.id == id){ return type;}
        }
        
        return null;
    }

    public int getId(){
        return id;
    }
}
