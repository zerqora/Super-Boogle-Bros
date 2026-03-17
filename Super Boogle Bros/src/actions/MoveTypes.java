package actions;

public enum MoveTypes 
{
    
    IDLE(0);


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
