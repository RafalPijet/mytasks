package com.lopez.mytasks.controller;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("This id not exists!!!");
    }
}