package com.example.project;

public class FilterCommand {
    private String filterName;
    private boolean command;

    public FilterCommand(String filterName, boolean command) {
        this.filterName = filterName;
        this.command = command;
    }

    public String getFilterName() {
        return filterName;
    }

    public boolean isCommand() {
        return command;
    }
}
