package shared.transferobjects;

import java.io.Serializable;

public class Message implements Serializable {

    private String message;
    private int id;

    public Message(String messageBody, int id){

        this.message= messageBody;
        this.id = id;
    }

    public int getId () {
        return id;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString() {
        return id  +": " +
                message ;
    }
}
