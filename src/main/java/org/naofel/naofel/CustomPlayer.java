package org.naofel.naofel;

public class CustomPlayer {

    boolean isBreakingLog = false;
    public boolean getCanBreakLog(){
        return this.isBreakingLog;
    }

    public void setCanBreakLog(){
        this.isBreakingLog = !this.isBreakingLog;
    }
}
