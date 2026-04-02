package actions;

public enum MoveTypes 
{
    
    IDLE(0),
    WALK(1),
    RUN(2),
    JUMP(3),
    AIR_IDLE(4),
    BASIC_N(5),
    BASIC_F(6),
    BASIC_D(7),
    BASIC_U(8),
    STRONG_F(9),
    STRONG_U(10),
    STRONG_D(11),
    SPECIAL_F(12),
    SPECIAL_N(13),
    AIR_N(14),
    AIR_F(15),
    AIR_D(16),
    AIR_B(17),
    AIR_U(18); // add more i dont remember



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
