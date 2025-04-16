package src.mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {

    String fileName;
    boolean unsavedChanges;

    protected boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    protected String getFileName(){
        return fileName;
    }

    protected void setFileName(String fName){
        this.fileName = fName;
    }

    protected void setUnsavedChanges(boolean b){
        this.unsavedChanges = b;
    }

    public void changed(){
        unsavedChanges = true;
        notifySubscribers();
    }

}