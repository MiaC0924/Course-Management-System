package com.TeamProject.Observer;

import java.util.ArrayList;

public interface Subject {
    public void attachObserver(Observer o);

    public void detachObserver(Observer o);

    public void notifyAllObserver();
}
