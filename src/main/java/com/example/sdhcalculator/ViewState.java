package com.example.sdhcalculator;

import java.util.Stack;

public class ViewState {
    private static final ViewState instance = new ViewState();

    private final Stack<String> viewHistory = new Stack<>();

    private ViewState() {}

    public static ViewState getInstance() {
        return instance;
    }

    public Stack<String> getViewHistory() {
        return viewHistory;
    }
}
